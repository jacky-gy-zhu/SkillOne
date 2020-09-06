package com.skillone.common.remote;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionFactory {

    private static String connectionConfigFullPath = "/Users/jackyzhu/workspace/github/connectionConfig.properties";

    private static String ip;
    private static int port;
    private static String userName;
    private static String password;

    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = new FileInputStream(new File(connectionConfigFullPath));
            if (inputStream != null) {
                properties.load(inputStream);
            }
            ip = properties.getProperty("ip");
            port = Integer.parseInt(properties.getProperty("port"));
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RemoteConnection getShellConnection() {
        return new RemoteConnection(ip, userName, password);
    }

    public static RemoteConnection getFtpConnection() {
        return new RemoteConnection(ip, port, userName, password);
    }

}
