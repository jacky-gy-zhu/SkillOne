package com.skillone.common.remote.transfer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3DirectoryEntry;

public class DeployEngine {
	
	public DeployEngine( String ip, int port, String name, String pwd){
		this.ip = ip;
		this.port = port;
		this.name = name;
		this.pwd = pwd;
	}
	
	private String ip;
	private int port;
	private String name;
	private String pwd;
	private Connection conn;
	private SFTPv3Client sftpClient;
	private SCPClient client;
	private boolean initialized;
	private String error;
	private Map<String,Set<String>> cacheFileMap;
	
	public void init(){
		long begin = System.currentTimeMillis();
		System.out.print("\n正在建立远程连接...");
		conn = new Connection(ip, port);
		try {
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(name, pwd);
			if (isAuthenticated == false) {
				conn.close();
				System.err.println("authentication failed");
				throw new RuntimeException("authentication failed");
			}
			sftpClient = new SFTPv3Client(conn);
			client = new SCPClient(conn);
			cacheFileMap = new HashMap<String,Set<String>>();
			initialized = true;
			System.out.println("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
		} catch (IOException ex) {
			conn.close();
			Logger.getLogger(DeployEngine.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException(ex);
		}
	}
	
	public void close(){
		if(conn != null){
			conn.close();
		}
	}
	
	public boolean transfer(String localFullPath, String remoteFullPath, String[] ignoreFolders, String[] ignoreFiles, boolean force){
		if(!initialized){
			init();
		}
		long begin = System.currentTimeMillis();
		if(localFullPath != null){
			try{
				error = null;
				File rootFile = new File(localFullPath);
				List<String> ignoreFolderList = null;
				List<String> ignoreFileList = null;
				if(ignoreFolders != null){
					ignoreFolderList = Arrays.asList(ignoreFolders);
				}
				if(ignoreFiles != null){
					ignoreFileList = Arrays.asList(ignoreFiles);
				}
				crawling(rootFile, remoteFullPath, ignoreFolderList, ignoreFileList, force);
				System.out.println("最终远程复制文件夹"+localFullPath.substring(localFullPath.lastIndexOf("/"))+"成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
				return true;
			}catch(Exception e){
				System.out.println("最终远程复制文件夹"+localFullPath.substring(localFullPath.lastIndexOf("/"))+"失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
				e.printStackTrace();
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean transferFile(String localFileAbsPath, String remoteFileAbsPath){
		if(!initialized){
			init();
		}
		long begin = System.currentTimeMillis();
		System.out.print("\n正在远程复制单文件"+remoteFileAbsPath+"...");
		try {
			String remoteFileName = remoteFileAbsPath.substring(remoteFileAbsPath.lastIndexOf("/")+1);
			String remoteTargetDirectory = remoteFileAbsPath.substring(0, remoteFileAbsPath.lastIndexOf("/"));
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				// 速度太快，传送会报错：FileNotFoundException
			}
			client.put(localFileAbsPath, remoteFileName, remoteTargetDirectory, "0600");
			System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return true;
		} catch (IOException e) {
			System.out.print("失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			error = "Fail to create file > "+remoteFileAbsPath;
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> list(String remoteTargetDirectory){
		List<String> list = new ArrayList<String>();
		try{
			Vector<SFTPv3DirectoryEntry> vector = sftpClient.ls(remoteTargetDirectory);
			if(vector != null){
				for(SFTPv3DirectoryEntry entry : vector){
					if(!entry.attributes.isDirectory()){
						list.add(entry.filename);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private void crawling(File file, String remoteFilePath, List<String> ignoreFolders, List<String> ignoreFiles, boolean force){
		long begin = System.currentTimeMillis();
		if(file != null){
			String localFileAbsPath = file.getAbsolutePath();
			System.out.print("\n正在复制"+(file.isDirectory()?"目录":"文件")+remoteFilePath+"...");
			if(file.isDirectory() && (ignoreFolders!=null?((!ignoreFolders.contains(file.getAbsolutePath()))&&(!ignoreFolders.contains(file.getName()))):true)){
				try{
					sftpClient.mkdir(remoteFilePath, 6600);
					System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
				}catch(Exception e){
					try {
						sftpClient.ls(remoteFilePath);
						System.out.print("忽略 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
					} catch (IOException e1) {
						error = "Fail to create folder > "+remoteFilePath;
						throw new RuntimeException("Fail to create folder > "+remoteFilePath);
					}
				}
				File[] files = file.listFiles();
				if(files != null){
					for(File f : files){
						String newRemoteFilePath = remoteFilePath + File.separator + f.getName();
						crawling(f, newRemoteFilePath, ignoreFolders, ignoreFiles, force);
					}
				}
			}else if(!file.isDirectory() && (ignoreFiles!=null?((!ignoreFiles.contains(file.getAbsolutePath()))&&(!ignoreFiles.contains(file.getName()))):true)){
				String fileName = file.getName();
				String remoteTargetDirectory = remoteFilePath.replace(File.separator+fileName, "");
				boolean needToCreate = false;
				if(force){
					needToCreate = true;
				}else{
					try {
						if(cacheFileMap.get(remoteTargetDirectory) == null){
							Vector<SFTPv3DirectoryEntry> vector = sftpClient.ls(remoteTargetDirectory);
							Set<String> fileNames = new HashSet<String>();
							if(vector != null){
								for(SFTPv3DirectoryEntry entry : vector){
									if(!entry.attributes.isDirectory()){
										fileNames.add(entry.filename);
									}
								}
							}
							cacheFileMap.put(remoteTargetDirectory, fileNames);
						}
						Set<String> fileSet = cacheFileMap.get(remoteTargetDirectory);
						if(!fileSet.contains(fileName)){
							needToCreate = true;
						}
					} catch (IOException e) {
						error = "Fail to list file > "+remoteFilePath;
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if(needToCreate){
					try {
//						client.put(localFileAbsPath, remoteTargetDirectory);
						if(fileName.indexOf(".") > 0){
							client.put(localFileAbsPath, fileName, remoteTargetDirectory, "0600");
							System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
						}else{
							System.out.print("忽略 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
						}
					} catch (IOException e) {
						error = "Fail to create file > "+remoteFilePath;
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}else{
					System.out.print("忽略 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
				}
			}else{
				System.out.print("忽略 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			}
		}
	}

	public String getError() {
		return error;
	}
	
	
}
