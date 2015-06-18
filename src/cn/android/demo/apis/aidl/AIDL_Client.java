package cn.android.demo.apis.aidl;

import java.util.List;
import java.util.Random;

import cn.android.demo.apis.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 
 * TODO 有点问题，未启动服务
 * 
 * 1.写.aidl文件 2.Service 实现aidl.Stub的Binder的接口，在onBinder中返回此Binder对象
 * 
 * 
 * @author Elroy
 * 
 */
public class AIDL_Client extends Activity implements OnClickListener {

	private IRemoteService mService;
	private boolean mIsBound = false;
	private boolean mIsJoin = false;

	private IBinder mToken = new Binder();
	private Random mRand = new Random();

	private Button mJoinBtn;

	private ListView mList;

	private ArrayAdapter<String> mAdapter;

	private ServiceConnection mServiceConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Toast.makeText(AIDL_Client.this, "Service connected",
					Toast.LENGTH_SHORT).show();

			mService = IRemoteService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Toast.makeText(AIDL_Client.this, "Service disconnected",
					Toast.LENGTH_SHORT).show();

			mService = null;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aidi_main_activity);

		findViewById(R.id.aidl_bind).setOnClickListener(this);
		findViewById(R.id.aidl_unbind).setOnClickListener(this);
		findViewById(R.id.aidl_call).setOnClickListener(this);
		findViewById(R.id.aidl_get_participators).setOnClickListener(this);

		mList = (ListView) findViewById(R.id.aidl_list);
		mJoinBtn = (Button) findViewById(R.id.aidl_join);
		mJoinBtn.setOnClickListener(this);

		mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
		mList.setAdapter(mAdapter);
	}

	private void callRemote() {

		if (isServiceReady()) {
			try {
				int result = mService.someOperate(1, 2);
				Toast.makeText(this, "Remote call return: " + result,
						Toast.LENGTH_SHORT).show();
			} catch (RemoteException e) {
				e.printStackTrace();
				Toast.makeText(this, "Remote call error!", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	private boolean isServiceReady() {
		if (mService != null) {
			return true;
		} else {
			Toast.makeText(this, "Service is not available yet!",
					Toast.LENGTH_SHORT).show();
			return false;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mIsBound) {
			unbindService(mServiceConnection);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.aidl_bind:
			Intent intent = new Intent(IRemoteService.class.getName());
			intent.setClassName("cn.android.demo.apis.aidl",
					"cn.android.demo.apis.aidl.RemoteService");
			// intent.setPackage("com.race604.remoteservice");
			bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
			mIsBound = true;
			break;
		case R.id.aidl_unbind:
			if (mIsBound) {
				unbindService(mServiceConnection);
				mIsBound = false;
			}
			break;
		case R.id.aidl_call:
			callRemote();
			break;
		case R.id.aidl_join:
			toggleJoin();
			break;
		case R.id.aidl_get_participators:
			updateParticipators();
			break;
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void updateParticipators() {
		if (!isServiceReady()) {
			return;
		}

		try {
			List<String> participators = mService.getParticipators();
			mAdapter.clear();
			mAdapter.addAll(participators);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void toggleJoin() {
		if (!isServiceReady()) {
			return;
		}

		try {
			if (!mIsJoin) {
				String name = "Client:" + mRand.nextInt(10);
				mService.join(mToken, name);
				mJoinBtn.setText("离开！");
				mIsJoin = true;
			} else {
				mService.leave(mToken);
				mJoinBtn.setText("加入！");
				mIsJoin = false;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
