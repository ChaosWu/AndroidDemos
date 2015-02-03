package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Android 基础 Service使用
 * 
 * @author Elroy
 * 
 */
public class AndroidService extends Activity implements OnClickListener {
	public  final static String TAG = AndroidService.class.getSimpleName();
	
	private Button btStartService;
	private Button btStopService;
	private Button btBindService;
	private Button btUnbindService;

	private boolean serviceIsBinding;
	private MyService mService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_android_service);
		btStartService = (Button) findViewById(R.id.bt_start_service);
		btStopService = (Button) findViewById(R.id.bt_stop_service);
		btBindService = (Button) findViewById(R.id.bt_bind_service);
		btUnbindService = (Button) findViewById(R.id.bt_unbind_service);

		btStartService.setOnClickListener(this);
		btStopService.setOnClickListener(this);
		btBindService.setOnClickListener(this);
		btUnbindService.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_start_service:
			startService2();
			ToastUtil.showToast(this, "Start Service");
			break;
		case R.id.bt_stop_service:
			stopService2();
			ToastUtil.showToast(this, "Stop Service");
			break;
		case R.id.bt_bind_service:
			bindService2();
			ToastUtil.showToast(this, "Bind Service");

			break;
		case R.id.bt_unbind_service:
			unbindService2();
			ToastUtil.showToast(this, "Unbind Service");

			break;

		default:
			break;
		}
	}

	// ServiceConnection界面监视应用程序服务的状态
	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mService = null;
			// ToastUtil.showToast(AndroidService.this,
			// "onServiceDisconnected");
			Log.v(TAG, "onServiceDisconnected");
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mService = ((MyService.LocalBinder) service).getService();
			// ToastUtil.showToast(AndroidService.this, "onServiceConnected");
			Log.v(TAG, "onServiceConnected");

		}
	};

	private void unbindService2() {
		if (serviceIsBinding) {
			unbindService(serviceConnection);
			serviceIsBinding = false;
		}

	}

	private void bindService2() {
		Intent intent = new Intent(this, MyService.class);
		bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
		serviceIsBinding = true;
	}

	private void stopService2() {
		Intent intent = new Intent(this, MyService.class);
		stopService(intent);
	}

	private void startService2() {
		Intent intent = new Intent(this, MyService.class);
		startService(intent);
	}
}
