package com.skillone.common.remote;

import java.util.List;

public interface RemoteFtp {

    /**
     * list the file/folder name in remoteFullPath
     * @param remoteFullPath
     * @return list of file/folder name in remoteFullPath
     */
    List<String> list(String remoteFullPath);

    /**
     * list the subFolder in remoteFolderFullPath
     * @param remoteFolderFullPath
     * @param subFolderName
     * @return
     */
    List<String> list(String remoteFolderFullPath, String subFolderName);

    /**
     * Only list files
     * @param remoteFullPath
     * @return
     */
    List<String> listFiles(String remoteFullPath);

    /**
     * Only list folders
     * @param remoteFullPath
     * @return
     */
    List<String> listFolders(String remoteFullPath);

    /**
     * Get the file content
     * @param remoteFileFullPath
     * @return the content of file as String
     */
    String view(String remoteFileFullPath);

    /**
     * Upload local file to remote
     * @param localFileFullPath
     * @param remoteFileFullPath
     * @return true if localFile been placed; otherwise return false
     */
    boolean uploadFile(String localFileFullPath, String remoteFileFullPath);

    /**
     * Upload localFolder to remoteFolder(under remote folder)
     * @param localFolderFullPath
     * @param remoteFolderFullPath
     * @return
     */
    boolean uploadFolder(String localFolderFullPath, String remoteFolderFullPath);

    /**
     * Download remote file to local
     * @param remoteFileFullPath
     * @param localFileFullPath
     * @return true if remoteFile been placed; otherwise return false
     */
    boolean downloadFile(String remoteFileFullPath, String localFileFullPath);

}
