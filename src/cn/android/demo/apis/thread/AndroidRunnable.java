package cn.android.demo.apis.thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;

public class AndroidRunnable extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	class PhotoDecodeRunnable implements Runnable{

		@Override
		public void run() {
			//把当前线程变成后台执行线程
			android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
			SystemClock.sleep(11000);
			
			
			
		}
		
	}
}
