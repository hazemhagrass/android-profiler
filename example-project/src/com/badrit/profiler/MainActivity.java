package com.badrit.profiler;

import java.util.Hashtable;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Profiler prof = new Profiler(this);
		Hashtable<String, Integer> cpuData = prof.getCpuStatistics();
		Hashtable<String, Long> memData = prof.getMemoryStatistics();
		Hashtable<String, String> batteryData = prof.getBatteryStatus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
