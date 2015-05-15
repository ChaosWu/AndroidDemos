package cn.android.demo.apis.thread;

import java.util.concurrent.locks.Lock;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.util.Pools.SynchronizedPool;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 同步方法,线程之间共享对象
 * 
 * @author Elroy
 * 
 */
public class ThreadSynchronizedMethods extends Activity {

	TextView infoA;
	TextView infoB;

	String msgA;
	String msgB;

	Button btStart;

	ShareClass obj = new ShareClass(10);

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_android_synchronized_methods);
		infoA = $id(R.id.share_object_between_thread_synchronized_method_infoa);
		infoB = $id(R.id.share_object_between_thread_synchronized_method_infob);
		btStart = $id(R.id.share_object_between_thread_synchronized_method_buttonstart);
		btStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				msgA = "Therad A\n";
				msgB = "Thread B\n";

				infoA.setText(msgA);
				infoB.setText(msgB);

				new Thread(new Runnable() {
					boolean stop = false;

					@Override
					public void run() {
						while (!stop) {
							if (obj.getCounter() > 0) {
								msgA += "A:" + obj.delayDecCounter3(2500) + "\n";

								SystemClock.sleep(3000);

								ThreadSynchronizedMethods.this
										.runOnUiThread(new Runnable() {

											@Override
											public void run() {
												infoA.setText(msgA);
											}
										});
							} else {
								stop = true;
							}
						}
					}
				}).start();

				new Thread(new Runnable() {
					boolean stop = false;

					@Override
					public void run() {
						while (!stop) {
							if (obj.getCounter() > 0) {
								msgB += "B:" + obj.delayDecCounter3(500) + "\n";

								SystemClock.sleep(500);

								ThreadSynchronizedMethods.this
										.runOnUiThread(new Runnable() {

											@Override
											public void run() {
												infoB.setText(msgB);
											}
										});

							} else {
								stop = true;
							}
						}
					}
				}).start();
			}
		});

	}

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}

	public class ShareClass {
		int counter;
		Lock lock;

		public ShareClass(int c) {
			this.counter = c;
		}

		public int getCounter() {
			return counter;
		}

		// 同步方法
		public synchronized int delayDecCounter2(int delay) {
			int tmpCounter = counter;

			SystemClock.sleep(delay);

			tmpCounter--;
			counter = tmpCounter;

			return counter;
		}

		// 同步代码块
		public int delayDecCounter(int delay) {

			SystemClock.sleep(100);
			synchronized (this) {
				int tmpCounter = counter;
				SystemClock.sleep(delay);
				tmpCounter--;
				counter = tmpCounter;

				return counter;
			}
		}

		// Lock更加强悍
		public int delayDecCounter3(int delay) {

			SystemClock.sleep(100);

			int tmpCounter;
			lock.lock();
			try {
				tmpCounter = counter;
				SystemClock.sleep(delay);
				tmpCounter--;
				counter = tmpCounter;
			} finally {
				lock.unlock();
			}
			return counter;
		}
	}
}
