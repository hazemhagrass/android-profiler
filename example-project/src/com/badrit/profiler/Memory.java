package com.badrit.profiler;

import java.io.RandomAccessFile;

class Memory {
	protected Memory(){
		
	}
	
	protected long[] getUsage() {
		//[usedSize, freeSize, totalSize]
		long[] memoryInfo = new long[3];
	    try {
	    	//system total mempry
			RandomAccessFile reader = null;
		    String line = null;
		    reader = new RandomAccessFile("/proc/meminfo", "r");
		    line = reader.readLine();
		    memoryInfo[2] = Long.parseLong(line.replaceAll("MemTotal:", "").replaceAll("kB", "").trim()) / 1024;
		    //memory free
		    line = reader.readLine();
		    memoryInfo[1] = Long.parseLong(line.replaceAll("MemFree:", "").replaceAll("kB", "").trim());
		    //Buffers
		    line = reader.readLine();
		    memoryInfo[1] = memoryInfo[1] + Long.parseLong(line.replaceAll("Buffers:", "").replaceAll("kB", "").trim());
		    //cached
		    line = reader.readLine();
		    memoryInfo[1] = memoryInfo[1] + Long.parseLong(line.replaceAll("Cached:", "").replaceAll("kB", "").trim());
		    memoryInfo[1] /= 1024;
		    
	        memoryInfo[0] = memoryInfo[2] - memoryInfo[1];
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return memoryInfo;
	}
}
