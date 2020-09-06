package com.skillone.common.remote.transfer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class CommandEngine {

	public CommandEngine(String ip, String name, String pwd){
		this.ip = ip;
		this.name = name;
		this.pwd = pwd;
	}
	
	private String ip;
	private String name;
	private String pwd;
	private Connection conn;
	private Session sess;
	private boolean initialized;
	private StringBuilder logBuilder;
	
	public void init(){
		long begin = System.currentTimeMillis();
		System.out.print("\n正在建立远程连接...");
		conn = new Connection(ip);
		try {
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(name, pwd);
			if (isAuthenticated == false) {
				conn.close();
				System.err.println("authentication failed");
				throw new RuntimeException("authentication failed");
			}
			initialized = true;
			System.out.println("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
		} catch (IOException ex) {
			conn.close();
			Logger.getLogger(CommandEngine.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException(ex);
		}
	}
	
	public void close(){
		if(conn != null){
			conn.close();
		}
	}
	
	public boolean isConnected(){
		if(initialized){
			return true;
		}else{
			try{
				init();
				return true;
			}catch(Exception e){
				return false;
			}
		}
	}
	
	private boolean execCommand(String command){
		if(!initialized){
			init();
		}
//		long begin = System.currentTimeMillis();
//		System.out.print("\n正在远程执行命令"+command+"...");
		BufferedReader br = null;
		InputStream stdout = null;
		try {
			sess = conn.openSession();
			sess.execCommand(command);
			logBuilder = new StringBuilder();
			logBuilder.append("\n");
			logBuilder.append(command);
			logBuilder.append("\n");
			stdout = new StreamGobbler(sess.getStdout());
			br = new BufferedReader(new InputStreamReader(stdout));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				logBuilder.append(line);
				logBuilder.append("\n");
			}
//			System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.print("失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return false;
		} finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				stdout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sess.close();
		}
	}
	
	public String getRemoteFileContent(String fileAbsPath){
		if(!initialized){
			init();
		}
		long begin = System.currentTimeMillis();
		System.out.print("\n正在获取远程文件内容"+fileAbsPath+"...");
		BufferedReader br = null;
		InputStream stdout = null;
		try {
			sess = conn.openSession();
			sess.execCommand("cat "+fileAbsPath);
			StringBuilder contentBuilder = new StringBuilder();
			stdout = new StreamGobbler(sess.getStdout());
			br = new BufferedReader(new InputStreamReader(stdout));
			boolean isFirst = true;
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				if(!isFirst){
					contentBuilder.append("\n");
				}
				contentBuilder.append(line);
				isFirst = false;
			}
			System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return contentBuilder.toString();
		} catch (Exception e) {
			System.out.print("失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			e.printStackTrace();
			return null;
		} finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				stdout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sess.close();
		}
	}
	
	public boolean killPort(int port){
		if(!initialized){
			init();
		}
		long begin = System.currentTimeMillis();
		System.out.print("\n正在关闭端口"+port+"...");
		String keyword = ":"+port+" ";
		boolean success = execCommand("netstat -noap");
		if(success){
			int pid = TransferUtils.getPidFromLog(keyword, getLogs());
			if(pid > 0){
				success = execCommand("ps -ef");
				if(success){
					Set<Integer> pidSet = TransferUtils.getPidsFromPs(pid, getLogs());
					pidSet.add(pid);
					// kill pid
					String comm = "";
					for(Integer pId : pidSet){
						comm += pId+" ";
					}
					success = execCommand("kill -9 "+comm);
					if(success){
						try {
							Thread.sleep(10*1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
					}else{
						System.out.print("失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
					}
					return success;
				}else{
					System.out.print("检查端口引用失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
					return false;
				}
			}else{
				System.out.print("忽略 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
				return true;
			}
		}else{
			System.out.print("再次检查端口失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return false;
		}
	}
	
	public boolean unZipTarget(String fileAbsPath, String targetDir){
		if(!initialized){
			init();
		}
		long begin = System.currentTimeMillis();
		System.out.print("\n正在解压文件"+fileAbsPath+"到"+targetDir+"...");
		boolean success = execCommand("tar -xzvf "+fileAbsPath+" -C "+targetDir);
		if(success){
			System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return true;
		}else{
			System.out.print("失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return false;
		}
	}
	
	public boolean renameTarget(String src, String dest){
		if(!initialized){
			init();
		}
		long begin = System.currentTimeMillis();
		System.out.print("\n正在重命名文件"+dest+"...");
		boolean success = execCommand("mv "+src+" "+dest);
		if(success){
			System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return true;
		}else{
			System.out.print("失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return false;
		}
	}
	
	public boolean touch(String fileAbsPath){
		if(!initialized){
			init();
		}
		long begin = System.currentTimeMillis();
		System.out.print("\n正在touch文件"+fileAbsPath+"...");
		boolean success = execCommand("touch "+fileAbsPath);
		if(success){
			System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return true;
		}else{
			System.out.print("失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return false;
		}
	}
	
	public boolean removeTarget(String target){
		if(!initialized){
			init();
		}
		long begin = System.currentTimeMillis();
		System.out.print("\n正在删除文件(夹)"+target+"...");
		boolean success = execCommand("rm -rf "+target);
		if(success){
			System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return true;
		}else{
			System.out.print("失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return false;
		}
	}
	
	public boolean exe(String command){
		if(!initialized){
			init();
		}
		long begin = System.currentTimeMillis();
		System.out.print("\n正在执行"+command+"...");
		boolean success = execCommand(command);
		if(success){
			System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return true;
		}else{
			System.out.print("失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return false;
		}
	}
	
	public boolean startupTomcat(String tomcatHomePath){
		if(!initialized){
			init();
		}
		long begin = System.currentTimeMillis();
		System.out.print("\n正在启动网站服务...");
		boolean success = execCommand(tomcatHomePath+"/bin/startup.sh");
		if(success){
			try {
				Thread.sleep(10*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return true;
		}else{
			System.out.print("失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return false;
		}
	}
	
	public boolean shutdownTomcat(String tomcatHomePath){
		if(!initialized){
			init();
		}
		long begin = System.currentTimeMillis();
		System.out.print("\n正在关闭网站服务...");
		boolean success = execCommand(tomcatHomePath+"/bin/shutdown.sh ");
		if(success){
			try {
				Thread.sleep(10*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return true;
		}else{
			System.out.print("失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return false;
		}
	}
	
	public boolean isPortOpen(int port){
		long begin = System.currentTimeMillis();
		System.out.print("\n正在检查端口"+port+"是否关闭...");
		Socket clientSocket = null;
		try{
			clientSocket = new Socket(ip, port);
			System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return true;
		}catch(Exception e){
			System.out.print("失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
			return false;
		}finally{
			if(clientSocket != null){
				try {
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getLogs(){
		return logBuilder.toString();
	}
	
}
