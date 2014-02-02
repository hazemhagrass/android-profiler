android-profiler
========

profiler lib to retrieve cpu, memory and battery info for android device

Example Usage: 

```
	Profiler prof = new Profiler(this);
	Hashtable<String, Integer> cpuData = prof.getCpuStatistics();
	Hashtable<String, Long> memData = prof.getMemoryStatistics();
	Hashtable<String, String> batteryData = prof.getBatteryStatus();
});
```
