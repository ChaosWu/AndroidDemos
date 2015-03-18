package cn.android.demo.apis.thread;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 其中在同一个线程的不同开发层次共享数据
 * 
 * 使用ThreadLocal的步骤:
 * 
 * 1建立一个类,在其中封装静态的ThreadLocal变量,使其成为一个数据共享环境
 * 
 * 2在类中实现ThreadLoca变量的静态方法(设值与取值)
 * 
 * @author Elroy
 * 
 */
public class UseThreadLocal extends Activity {
	private Button bt;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_use_thread_local);
		bt = (Button) findViewById(R.id.bt_thread_local);
		tv = (TextView) findViewById(R.id.tv_thread_local);

		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				MyThread1 tt = new MyThread1();
				// MyThread t2 = new MyThread();
				// MyThread t3 = new MyThread();
				Thread t1 = new Thread(tt, "T1");
				Thread t2 = new Thread(tt, "T2");
				Thread t3 = new Thread(tt, "T3");

				t1.start();
				t2.start();
				t3.start();

			}
		});

	}

	public class MyThread implements Runnable {
		public Integer num;

		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				num = SafeThread.getCounterContextNext();

				Log.v("DDD", "num:" + num + "   id:"
						+ Thread.currentThread().getName());
			}
		}

	}

	public class MyThread1 extends Thread {
		public Integer num;

		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				num = SafeThread.getCounterContextNext();

				Log.v("DDD", "num:" + num + "   id:"
						+ Thread.currentThread().getName());
			}
		}

	}

}

class SafeThread {
	private static ThreadLocal<Integer> counterContext = new ThreadLocal<Integer>();

	public static Integer getCounterContext() {

		if (counterContext.get() != null) {
			return counterContext.get();
		}
		setCounterContext(1);
		return counterContext.get();
	}

	public static void setCounterContext(Integer value) {
		counterContext.set(value);
	}

	public static Integer getCounterContextNext() {
		counterContext.set(getCounterContext() + 1);
		return counterContext.get();
	}
}
