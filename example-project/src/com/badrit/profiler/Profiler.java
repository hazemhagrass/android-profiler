package com.badrit.profiler;

import java.util.Hashtable;

public class Profiler {
	
	public Hashtable<String, Integer> getCpuStatistics(){
		Hashtable<String, Integer> statistics = new Hashtable<String, Integer>(); 
		
		Cpu cpu = new Cpu();
		
		//[user, system, iqw, irq]
		int[] cpuUsageAsInt = cpu.getUsage();
		
		statistics.put("user", cpuUsageAsInt[0]);
		statistics.put("system", cpuUsageAsInt[1]);
		statistics.put("iqw", cpuUsageAsInt[2]);
		statistics.put("irq", cpuUsageAsInt[3]);
		
		return statistics;
	}
	
	public Hashtable<String, Long> getMemoryStatistics(){
		Hashtable<String, Long> statistics = new Hashtable<String, Long>(); 
		
		Memory mem = new Memory();
		
		//[usedSize, freeSize, totalSize]
		long[] memoryInfo = mem.getUsage();
		
		statistics.put("used", memoryInfo[0]);
		statistics.put("free", memoryInfo[1]);
		statistics.put("total", memoryInfo[2]);
		
		return statistics;
	}
}
