package com.badrit.profiler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Cpu {

	protected Cpu() {
	}

	private boolean isGeneralStatisticsInfo(String info){
		if((info.indexOf("user") != -1 && info.indexOf("system") != -1)
				|| (info.indexOf("usr") != -1 && info.indexOf("sys") != -1))
			return true;
		return false;
	}
	//[user, system, iqw, irq]
	private int[] getGeneralStatisticsInfo(String info){
		info = info.replaceAll(",", "");
		info = info.replaceAll("cpu:", "");
		info = info.replaceAll("user", "");
		info = info.replaceAll("usr", "");
		info = info.replaceAll("system", "");
		info = info.replaceAll("sys", "");
		info = info.replaceAll("iow", "");
		info = info.replaceAll("irq", "");
		info = info.replaceAll("nic", "");
		info = info.replaceAll("idle", "");
		info = info.replaceAll("io", "");
		info = info.replaceAll("s", "");
		
		info = info.replaceAll("%", "");
		for (int i = 0; i < 10; i++) {
			info = info.replaceAll("  ", " ");
		}
		info = info.trim();
		String[] myString = info.split(" ");
		int[] cpuUsageAsInt = new int[myString.length];
		for (int i = 0; i < myString.length; i++) {
			myString[i] = myString[i].trim();
			cpuUsageAsInt[i] = (int)Double.parseDouble(myString[i]);
		}
		return cpuUsageAsInt;
	}
	
	protected int[] getUsage() {
		int[] cpuUsageAsInt = null;
		try {
			// -m 10, how many entries you want, -d 1, delay by how much, -n 1,
			// number of iterations
			Process p = Runtime.getRuntime().exec("top -d 1 -n 1");

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			
			String line = reader.readLine();
			while (line != null) {
				line = line.toLowerCase();
				if(isGeneralStatisticsInfo(line)){
					cpuUsageAsInt = getGeneralStatisticsInfo(line);
					break;
				}
					
				line = reader.readLine();
			}

			p.waitFor();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return cpuUsageAsInt;
	}
}