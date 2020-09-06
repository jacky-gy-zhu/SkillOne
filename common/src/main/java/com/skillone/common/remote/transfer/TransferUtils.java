package com.skillone.common.remote.transfer;

import java.util.HashSet;
import java.util.Set;

public class TransferUtils {
	
	public static int getPidFromLog(String keyword, String log){
		if(log != null){
			String[] logLines = log.split("\n");
			if(logLines != null){
				for(String line : logLines){
					if(line.contains(keyword)){
						// in this line
						String[] blocks = line.split(" ");
						if(blocks != null){
							for(String block : blocks){
								if(block != null && !block.isEmpty() && block.contains("/") && block.split("/").length == 2){
									String _pid = block.split("/")[0];
									try{
										int pid = Integer.parseInt(_pid);
										if(pid > 0){
											return pid;
										}
									}catch(Exception e){
									}
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}
	
	public static Set<Integer> getPidsFromPs(int pid, String log){
		String keyword = " "+pid+" ";
		Set<Integer> relatedPidSet = new HashSet<Integer>();
		if(log != null){
			String[] logLines = log.split("\n");
			if(logLines != null){
				for(String line : logLines){
					if(line.contains(keyword)){
						// in this line
						String[] blocks = line.split(" ");
						if(blocks != null){
							for(String block : blocks){
								if(block != null && !block.isEmpty()){
									try{
										int relatedPid = Integer.parseInt(block);
										relatedPidSet.add(relatedPid);
										break;
									}catch(Exception e){
									}
								}
							}
						}
					}
				}
			}
		}
		return relatedPidSet;
	}
	
	public static int generateInstalledKey(String appAbsPath){
		return appAbsPath.hashCode();
	}
	
	
}
