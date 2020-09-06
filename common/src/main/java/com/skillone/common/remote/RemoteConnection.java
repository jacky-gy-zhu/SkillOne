package com.skillone.common.remote;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RemoteConnection {

    private String userName;
    private Connection conn;
    private SFTPv3Client sftpClient;
    private SCPClient client;
    private Map<String,Set<String>> cacheFileMap;

    RemoteConnection(String ip, String userName, String password) {
        long begin = System.currentTimeMillis();
        System.out.print("\n正在建立远程连接...");
        conn = new Connection(ip);
        try {
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(userName, password);
            if (isAuthenticated == false) {
                conn.close();
                System.err.println("authentication failed");
                throw new RuntimeException("authentication failed");
            }
            System.out.println("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
            this.userName = userName;
        } catch (IOException ex) {
            conn.close();
            throw new RuntimeException(ex);
        }
    }

    RemoteConnection(String ip, int port, String userName, String password) {
        long begin = System.currentTimeMillis();
        System.out.print("\n正在建立远程连接...");
        conn = new Connection(ip, port);
        try {
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(userName, password);
            if (isAuthenticated == false) {
                conn.close();
                System.err.println("authentication failed");
                throw new RuntimeException("authentication failed");
            }
            sftpClient = new SFTPv3Client(conn);
            client = new SCPClient(conn);
            cacheFileMap = new HashMap();
            System.out.println("成功 耗时:"+(System.currentTimeMillis()-begin)/1000+"秒");
            this.userName = userName;
        } catch (IOException ex) {
            conn.close();
            throw new RuntimeException(ex);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public SFTPv3Client getSftpClient() {
        return sftpClient;
    }

    public SCPClient getClient() {
        return client;
    }

    public Map<String, Set<String>> getCacheFileMap() {
        return cacheFileMap;
    }

    public String getUserName() {
        return userName;
    }
}
