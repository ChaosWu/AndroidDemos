package cn.android.demo.apis.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

/**
 * 一个计数信号量。从概念上讲，信号量维护了一个许可集。如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。每个 release()
 * 添加一个许可，从而可能释放一个正在阻塞的获取者。但是，不使用实际的许可对象，Semaphore
 * 只对可用许可的号码进行计数，并采取相应的行动。拿到信号量的线程可以进入代码，否则就等待。通过acquire()和release()获取和释放访问许可。
 * 
 * @author Elroy
 * 
 */
public class SemaphoreActivity extends Activity {
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tv = new TextView(this);
		tv.setMovementMethod(new ScrollingMovementMethod());

		setContentView(tv);
		test();

	}

	private void test() {
		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		// 只能5个线程同事访问
		final Semaphore sp = new Semaphore(5);
		for (int i = 0; i < 1000; i++) {
			final int NO = i;
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try {
						// 获取许可
						sp.acquire();
						SemaphoreActivity.this.runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								tv.append("\n Accessing:" + NO);
							}
						});
						SystemClock.sleep((long) (Math.random() * 1000 ));

						// 访问完后，释放，如果屏蔽下面的语句，则只能输入5条记录，之后线程一直阻塞
						sp.release();

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			};
			exec.execute(runnable);
		}
		// 退出线程池
		exec.shutdown();
	}
}
