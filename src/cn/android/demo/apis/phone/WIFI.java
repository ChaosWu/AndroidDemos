package cn.android.demo.apis.phone;

import java.util.ArrayList;
import java.util.List;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class WIFI extends Activity {

	private TextView textWifiManager;
	private TextView textWifiInfo;
	private TextView textIp;
	private TextView textState;

	private ListView listView;

	Button OnWifi;
	Button OffWifi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.phone_android_wifi);

		textWifiManager = (TextView) findViewById(R.id.WifiManager);
		textWifiInfo = (TextView) findViewById(R.id.WifiInfo);
		textIp = (TextView) findViewById(R.id.Ip);

		textState = (TextView) findViewById(R.id.tv_wifi_state);

		listView = (ListView) findViewById(R.id.networkinfo_list);

		OnWifi = (Button) findViewById(R.id.onwifi);
		OffWifi = (Button) findViewById(R.id.offwifi);

		// ConnectivityManager主要管理和网络连接相关的操作
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		WifiInfo info = wifiManager.getConnectionInfo();

		NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

		List<String> listNetworkInfo = new ArrayList<String>();
		for (int i = 0; i < networkInfos.length; i++) {
			String strNetworkState = networkInfos[i].getTypeName() + ":"
					+ networkInfos[i].getDetailedState();

			 listNetworkInfo.add(strNetworkState);
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listNetworkInfo);
		listView.setAdapter(adapter);
		listView.setTextFilterEnabled(true);

		if (networkInfo.isConnected()) {
			int ip = info.getIpAddress();

			textWifiManager.setText(wifiManager.toString());
			textWifiInfo.setText(info.toString());

			textIp.setText(intToIp(ip) + "\n");
			textIp.append(int2Ip(ip) + "\n");
			textIp.append(toIp(ip) + "\n");
			textIp.append(ip + "\n");
		}

		registerReceiver(wifiStateChangedReceiver, new IntentFilter(
				WifiManager.WIFI_STATE_CHANGED_ACTION));

		OnWifi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				WifiManager wifiManager = (WifiManager) getBaseContext()
						.getSystemService(Context.WIFI_SERVICE);
				wifiManager.setWifiEnabled(true);
			}
		});

		OffWifi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				WifiManager wifiManager = (WifiManager) getBaseContext()
						.getSystemService(Context.WIFI_SERVICE);
				wifiManager.setWifiEnabled(false);
			}
		});
	}

	private BroadcastReceiver wifiStateChangedReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			int extraState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
					WifiManager.WIFI_STATE_UNKNOWN);

			switch (extraState) {
			case WifiManager.WIFI_STATE_DISABLED:
				textState.setText("WIFI_STATE_DISABLED");
				break;
			case WifiManager.WIFI_STATE_DISABLING:
				textState.setText("WIFI_STATE_DISABLING");
				break;
			case WifiManager.WIFI_STATE_ENABLED:

				textState.setText("WIFI_STATE_ENABLED");
				break;
			case WifiManager.WIFI_STATE_ENABLING:

				textState.setText("WIFI_STATE_ENABLING");
				break;
			case WifiManager.WIFI_STATE_UNKNOWN:
				textState.setText("WIFI_STATE_UNKNOWN");

				break;

			default:
				break;
			}

		}
	};

	private String toIp(int ip) {
		int intIp3 = ip / 0x1000000;
		int intIp3mod = ip % 0x1000000;

		int intIp2 = intIp3mod / 0x10000;
		int intIp2mod = intIp3mod % 0x10000;

		int intip1 = intIp2mod / 0x100;
		int intip0 = intIp2mod % 0x100;

		Log.v("DDD", "ip3:" + intIp3);
		Log.v("DDD", "intIp3mod:" + intIp3mod);
		Log.v("DDD", "ip2:" + intIp2);
		Log.v("DDD", "intIp2mod:" + intIp2mod);
		Log.v("DDD", "ip1:" + intip1);
		Log.v("DDD", "ip0:" + intip0);

		StringBuilder sb = new StringBuilder();
		sb.append(intip0).append(".");
		sb.append(intip1).append(".");
		sb.append(intIp2).append(".");
		sb.append(intIp3);

		return sb.toString();
	}

	private String int2Ip(int ip) {
		StringBuilder sb = new StringBuilder();
		sb.append(ip & 0xFF).append(".");
		sb.append((ip >> 8) & 0xFF).append(".");
		sb.append((ip >> 16) & 0xFF).append(".");
		sb.append((ip >> 24) & 0xFF);
		return sb.toString();
	}

	private String intToIp(int i) {

		return Formatter.formatIpAddress(i);

	}
}
