package cn.android.demo.apis.app;

import cn.android.demo.apis.MainActivity;
import cn.android.demo.utils.ToastUtil;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	public  final static String TAG = MyService.class.getSimpleName();
	@Override
	public IBinder onBind(Intent intent) {
		// ToastUtil.showToast(this, "MyService onBind(Intent intent)");
		Log.v(TAG, "MyService onBind(Intent intent)");

		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// ToastUtil.showToast(this, "MyService onCreate()");
		Log.v(TAG, "MyService onCreate()");

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// ToastUtil.showToast(this, "MyService onDestroy()");
		Log.v(TAG, "MyService onDestroy()");

	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
		// ToastUtil.showToast(this, "MyService onRebind()");
		Log.v(TAG, "MyService onRebind()");
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		// ToastUtil.showToast(this, "MyService onStart()");
		Log.v(TAG, "MyService onStart(Intent intent, int" + startId + ")");

	}

	@Override
	public boolean onUnbind(Intent intent) {
		// ToastUtil.showToast(this, "MyService onUnbind()");
		Log.v(TAG, "MyService onUnbind(Intent intent)");
		return super.onUnbind(intent);

	}

	public class LocalBinder extends Binder {
		MyService getService() {
			return MyService.this;
		}
	}

}
