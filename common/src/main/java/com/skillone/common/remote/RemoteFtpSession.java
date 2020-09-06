package com.skillone.common.remote;

import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import com.skillone.common.logger.Log;
import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RemoteFtpSession extends AbstractRemoteSession implements RemoteFtp {

    public RemoteFtpSession(RemoteConnection connection) {
        super(connection);
    }

    @Override
    public List<String> list(String remoteFullPath) {
        return listInternal(remoteFullPath, null);
    }

    @Override
    public List<String> listFiles(String remoteFullPath) {
        return listInternal(remoteFullPath, false);
    }

    @Override
    public List<String> listFolders(String remoteFullPath) {
        return listInternal(remoteFullPath, true);
    }

    private List<String> listInternal(String remoteFullPath, Boolean isDirectory) {

        List<String> list = new ArrayList();
        try{
            Vector<SFTPv3DirectoryEntry> vector = connection.getSftpClient().ls(remoteFullPath);
            Log.println(promptFtp() + "Listing directory \"" + remoteFullPath + "\"");
            if(vector != null){
                for(SFTPv3DirectoryEntry entry : vector){
                    boolean condition = true;
                    if (isDirectory != null && isDirectory == Boolean.TRUE) {
                        condition = entry.attributes.isDirectory();
                    } else if (isDirectory != null && isDirectory == Boolean.FALSE) {
                        condition = !entry.attributes.isDirectory();
                    }
                    if(condition){
                        if(!".".equals(entry.filename) && !"..".equals(entry.filename))
                        list.add(entry.filename);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            Log.error(e.getMessage());
        }
        if (CollectionUtils.isNotEmpty(list)) {
            Log.println(list);
            return list;
        } else {
            return null;
        }
    }

    @Override
    public List<String> list(String remoteFolderFullPath, String subFolderName) {
        return list(remoteFolderFullPath + File.separator + subFolderName);
    }

    @Override
    public String view(String remoteFileFullPath) {
        return null;
    }

    @Override
    public boolean uploadFile(String localFileFullPath, String remoteFileFullPath) {
        long begin = System.currentTimeMillis();
        Log.println(promptFtp() + "Uploading file \"" + localFileFullPath + "\" to \"" + remoteFileFullPath + "\"");
        try {
            String remoteFileName = remoteFileFullPath.substring(remoteFileFullPath.lastIndexOf("/")+1);
            String remoteTargetDirectory = remoteFileFullPath.substring(0, remoteFileFullPath.lastIndexOf("/"));
            File file = new File(localFileFullPath);
            if (file.exists()) {
                connection.getClient().put(localFileFullPath, remoteFileName, remoteTargetDirectory, "0600");
                Log.println("Upload file successful! Total cost:"+(System.currentTimeMillis()-begin)/1000+"s");
                return true;
            } else {
                Log.println("Upload failed! Local file not exist!");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean uploadFolder(String localFolderFullPath, String remoteFolderFullPath) {
        return uploadFolder(localFolderFullPath, remoteFolderFullPath, null, null, true);
    }

    private boolean uploadFolder(String localFolderFullPath, String remoteFolderFullPath, String[] ignoreFolders, String[] ignoreFiles, boolean force) {
        long begin = System.currentTimeMillis();
        if(localFolderFullPath != null){
            try{
                File rootFile = new File(localFolderFullPath);
                List<String> ignoreFolderList = null;
                List<String> ignoreFileList = null;
                if(ignoreFolders != null){
                    ignoreFolderList = Arrays.asList(ignoreFolders);
                }
                if(ignoreFiles != null){
                    ignoreFileList = Arrays.asList(ignoreFiles);
                }
                crawling(rootFile, remoteFolderFullPath, ignoreFolderList, ignoreFileList, force);
                System.out.println("最终远程复制文件夹"+localFolderFullPath.substring(localFolderFullPath.lastIndexOf("/"))+"成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
                return true;
            }catch(Exception e){
                System.out.println("最终远程复制文件夹"+localFolderFullPath.substring(localFolderFullPath.lastIndexOf("/"))+"失败 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public boolean downloadFile(String remoteFileFullPath, String localFileFullPath) {
        return false;
    }

    private void crawling(File file, String remoteFilePath, List<String> ignoreFolders, List<String> ignoreFiles, boolean force){
        long begin = System.currentTimeMillis();
        if(file != null){
            String localFileAbsPath = file.getAbsolutePath();
            System.out.print("\n正在复制"+(file.isDirectory()?"目录":"文件")+remoteFilePath+"...");
            if(file.isDirectory() && (ignoreFolders!=null?((!ignoreFolders.contains(file.getAbsolutePath()))&&(!ignoreFolders.contains(file.getName()))):true)){
                try{
                    connection.getSftpClient().mkdir(remoteFilePath, 6600);
                    System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
                }catch(Exception e){
                    try {
                        connection.getSftpClient().ls(remoteFilePath);
                        System.out.print("忽略 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
                    } catch (IOException e1) {
                        Log.error(e1.getMessage());
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
                        if(connection.getCacheFileMap().get(remoteTargetDirectory) == null){
                            Vector<SFTPv3DirectoryEntry> vector = connection.getSftpClient().ls(remoteTargetDirectory);
                            Set<String> fileNames = new HashSet<String>();
                            if(vector != null){
                                for(SFTPv3DirectoryEntry entry : vector){
                                    if(!entry.attributes.isDirectory()){
                                        fileNames.add(entry.filename);
                                    }
                                }
                            }
                            connection.getCacheFileMap().put(remoteTargetDirectory, fileNames);
                        }
                        Set<String> fileSet = connection.getCacheFileMap().get(remoteTargetDirectory);
                        if(!fileSet.contains(fileName)){
                            needToCreate = true;
                        }
                    } catch (IOException e) {
                        Log.error(e.getMessage());
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
                if(needToCreate){
                    try {
//						client.put(localFileAbsPath, remoteTargetDirectory);
                        if(fileName.indexOf(".") > 0){
                            connection.getClient().put(localFileAbsPath, fileName, remoteTargetDirectory, "0600");
                            System.out.print("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
                        }else{
                            System.out.print("忽略 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.error(e.getMessage());
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
}
