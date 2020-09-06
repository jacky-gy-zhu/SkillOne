package com.skillone.common.remote;

import org.junit.Test;

import java.util.List;

public class ConnectionFactoryTest {

    @Test
    public void testRemoteShellSession() {

        RemoteShellSession shell = new RemoteShellSession(ConnectionFactory.getShellConnection());
        List<String> lineList = shell.exe("netstat -noap");
        shell.close();
    }

    @Test
    public void testRemoteFtpSession() {

        RemoteFtpSession ftp = new RemoteFtpSession(ConnectionFactory.getFtpConnection());
        ftp.list("/home/software");
        ftp.close();
    }

    @Test
    public void testRemoteFtpSessionUploadFile() {

        RemoteFtpSession ftp = new RemoteFtpSession(ConnectionFactory.getFtpConnection());
        ftp.uploadFile("/Users/jackyzhu/Downloads/Ying/Maths-20.7.wmv", "/home/temp/Maths-20.7.wmv");
        ftp.close();
    }

    @Test
    public void testRemoteFtpSessionUploadFolder() {

        RemoteFtpSession ftp = new RemoteFtpSession(ConnectionFactory.getFtpConnection());
        ftp.uploadFolder("/Users/jackyzhu/Documents/移民/grant", "/home/temp");
        ftp.close();
    }

}