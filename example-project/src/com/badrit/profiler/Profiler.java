package com.badrit.profiler;

import java.util.Hashtable;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class Profiler {
	private Context context;
	
	public Profiler(Context ctx){
		this.context = ctx;
	}
	
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
	
	public Hashtable<String, String> getBatteryStatus(){
		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = context.getApplicationContext().registerReceiver(null, ifilter);
		
		Hashtable<String, String> data = new Hashtable<String, String>(); 
		
		int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS,
				-1);
		
		boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
				|| status == BatteryManager.BATTERY_STATUS_FULL;
		
		data.put("is_charging", isCharging + "");
		
		// How are we charging?
		int chargePlug = batteryStatus.getIntExtra(
				BatteryManager.EXTRA_PLUGGED, -1);
		boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
		boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
		
		data.put("usb_charge", usbCharge + "");
		data.put("ac_charge", acCharge + "");
		
		int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL,
				-1);
		int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE,
				-1);
		
		data.put("level", level + "");
		data.put("scale", scale + "");
		
		return data;
	}
}
