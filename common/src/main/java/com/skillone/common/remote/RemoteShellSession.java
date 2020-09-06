package com.skillone.common.remote;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.google.common.collect.Lists;
import com.skillone.common.logger.Log;
import org.apache.commons.collections4.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class RemoteShellSession extends AbstractRemoteSession implements RemoteShell {

    public RemoteShellSession(RemoteConnection connection) {
        super(connection);
    }

    @Override
    public List<String> exe(String command) {

        BufferedReader br = null;
        InputStream stdout = null;
        Session sess = null;
        try {
            sess = connection.getConnection().openSession();
            sess.execCommand(command);
            stdout = new StreamGobbler(sess.getStdout());
            br = new BufferedReader(new InputStreamReader(stdout));
            Log.println(promptShell() + command);
            List<String> lineList = Lists.newArrayList();
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                lineList.add(line);
            }
            Log.println(lineList);
            if (CollectionUtils.isNotEmpty(lineList)) {
                return lineList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.println(e.getMessage());
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
        return null;
    }
}
