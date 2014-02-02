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
		
		while(true){
			Profiler prof = new Profiler();
			Hashtable<String, Integer> cpuData = prof.getCpuStatistics();
			Hashtable<String, Long> memData = prof.getMemoryStatistics();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
