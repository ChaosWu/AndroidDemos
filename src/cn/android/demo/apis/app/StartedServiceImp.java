package cn.android.demo.apis.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

/**
 * 每隔1000ms将一个数字+2 并通过广播发出去
 * 
 * @author Elroy
 * 
 */
public class StartedServiceImp extends Service {
	private static final String TAG = StartedServiceImp.class.getSimpleName();
	// 发送广播的action
	public static final String COUNT_ACTION = "cn.android.demo.apis.app.StartedServiceImp.COUNT_ACTION";

	// 因为Service是运行在UI线程，所以耗时的操作交个子线程完成
	private static CountTread mCountTread = null;
	// 数字索引
	private static int index = 0;

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");

		// 终止服务
		if (mCountTread != null) {
			// 发送一个中断请求线。的行为取决于这个线程的状态
			mCountTread.interrupt();
			mCountTread = null;
		}

		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (mCountTread != null) {
			Log.d(TAG, "mCountThread != null");
			index = 0;
			return START_STICKY;
		}
		// 首次运行时，创建并启动线程
		Log.d(TAG, "start thread");
		mCountTread = new CountTread();
		mCountTread.start();

		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind");
		return null;
	}

	private class CountTread extends Thread {

		@Override
		public void run() {
			index = 0;
			while (true) {
				index += 2;

				Intent intent = new Intent(COUNT_ACTION);
				intent.putExtra(COUNT_ACTION + ".NAME", index);
				sendBroadcast(intent);

				if (index >= 100) {
					if (mCountTread != null) {
						mCountTread = null;
					}
					return;

				}
				SystemClock.sleep(500);
			}
		}

	}

}
