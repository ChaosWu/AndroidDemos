package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AndroidBluetooth extends Activity {
	private int REQUEST_ENABLE_BT = 1;
	private int REQUEST_PAIRED_DEVICE = 2;
	private boolean openBluetooth = false;
	private TextView stateBluetooth;
	private Button listpaireddevices;

	private BluetoothAdapter bluetoothAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.phone_android_bluetooth);
		stateBluetooth = (TextView) findViewById(R.id.tv_bluetoothstate);
		listpaireddevices = (Button) findViewById(R.id.bt_listpaireddevices);

		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		listpaireddevices.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getBaseContext(),
						ListPairedDevicesActivity.class);
				startActivityForResult(intent, REQUEST_PAIRED_DEVICE);

			}
		});
		CheckBluetoothState();
	}

	private void CheckBluetoothState() {
		if (bluetoothAdapter == null) {
			stateBluetooth.setText("Bluetooth NOT state!");
		} else {
			if (bluetoothAdapter.isEnabled()) {
				if (bluetoothAdapter.isDiscovering()) {
					stateBluetooth
							.setText("Bluetooth is currently in device discovery process");
				} else {
					stateBluetooth.setText("Bluetooth is Enable");
					listpaireddevices.setEnabled(true);
				}

			} else {
				stateBluetooth.setText("Bluetooth is NOT Enabled");

				if (!openBluetooth) {
					Intent intent = new Intent(
							BluetoothAdapter.ACTION_REQUEST_ENABLE);
					startActivityForResult(intent, REQUEST_ENABLE_BT);

				}
			}
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_ENABLE_BT) {
			openBluetooth = true;
			CheckBluetoothState();
		}
		if (requestCode == REQUEST_PAIRED_DEVICE) {
			if (resultCode == RESULT_OK) {

			}

		}

	}
}
