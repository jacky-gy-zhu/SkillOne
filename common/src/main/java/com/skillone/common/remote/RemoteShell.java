package com.skillone.common.remote;

import java.util.List;

public interface RemoteShell {

    /**
     * Linux command
     * @param command linux command
     * @return console content as list
     */
    List<String> exe(String command);

}
