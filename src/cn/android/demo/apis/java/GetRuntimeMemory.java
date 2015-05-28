package cn.android.demo.apis.java;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 需要更多的内存用于应用程序运行，在application添加 android:largeHeap="true"
 * 
 * 
 * MemoryInfo.availMem: the available memory on the system.
 * 
 * MemoryInfo.totalMem: the total memory accessible by the kernel.
 * 
 * Runtime.maxMemory(): the maximum number of bytes the heap can expand to.
 * 
 * Runtime.totalMemory(): the current number of bytes taken by the heap.
 * 
 * Runtime.freeMemory(): the current number of those bytes actually used by live
 * objects.
 * 
 * @author Elroy
 * 
 */
public class GetRuntimeMemory extends Activity {
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Runtime runtime = Runtime.getRuntime();
		MemoryInfo memoryInfo = new MemoryInfo();

		ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		activityManager.getMemoryInfo(memoryInfo);

		TextView textView = new TextView(this);
		setContentView(textView);
		String info = "";
		long m = 1024 * 1024;

		info += "MemoryInfo.availMem = " + memoryInfo.availMem / m + "M \n"
				+ "\n";
		info += "MemoryInfo.totalMem = " + memoryInfo.totalMem / m + "M \n"
				+ "\n\n";// API 16

		info += "Total memory: " + runtime.totalMemory() / m + "M \n";
		info += "Free memory: " + runtime.freeMemory() / m + "M \n";
		info += "Max memory: " + runtime.maxMemory() / m + "M \n";
		textView.setText(info);
	}
}
