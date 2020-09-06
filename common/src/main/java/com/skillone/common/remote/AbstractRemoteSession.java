package com.skillone.common.remote;

public abstract class AbstractRemoteSession {

    protected RemoteConnection connection;

    public AbstractRemoteSession(RemoteConnection connection) {
        this.connection = connection;
    }

    protected String promptShell() {
        return "[" + connection.getUserName() + "@shell]# ";
    }

    protected String promptFtp() {
        return "[" + connection.getUserName() + "@ftp]> ";
    }

    public void close() {
        connection.getConnection().close();
    }
}
