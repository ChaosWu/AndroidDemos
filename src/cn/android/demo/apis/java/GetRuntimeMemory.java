package cn.android.demo.apis.java;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 需要更多的内存用于应用程序运行，在application添加 android:largeHeap="true"
 * 
 * @author Elroy
 * 
 */
public class GetRuntimeMemory extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textView = new TextView(this);
		setContentView(textView);
		String info = "";
		long m = 1024 * 1024;
		info += "Total memory: " + Runtime.getRuntime().totalMemory() / m
				+ "M \n";
		info += "Free memory: " + Runtime.getRuntime().freeMemory() / m
				+ "M \n";
		info += "Max memory: " + Runtime.getRuntime().maxMemory() / m + "M \n";
		textView.setText(info);
	}
}
