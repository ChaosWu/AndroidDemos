package cn.android.demo.apis.other;

import java.util.ArrayList;
import java.util.Set;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 是否支持 Bluetooth Low
 * 
 * @author Elroy
 * 
 */
public class BluetoothLowEnergy extends Activity {

	private static final int REQUEST_ENABLE_BT = 1;

	ListView listView;
	TextView textManufacturer;
	TextView textModel;
	TextView textSupportBT;
	TextView textSupportBTLE;

	BluetoothAdapter bluetoothAdapter;
	ArrayList<BluetoothDevice> pairedDeviceArrayList;
	ArrayAdapter<BluetoothDevice> pairedDeviceAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_android_bluetooth_low);
		textManufacturer = (TextView) findViewById(R.id.bluetooth_low_manufacturer);
		textModel = (TextView) findViewById(R.id.bluetooth_low_model);
		textSupportBT = (TextView) findViewById(R.id.bluetooth_low_supportbt);
		textSupportBTLE = (TextView) findViewById(R.id.bluetooth_low_supportbtle);

		listView = (ListView) findViewById(R.id.bluetooth_low_pairedlist);

		// Get brand, manufacturer and model of your device
		textManufacturer.setText(Build.BRAND + " : " + Build.MANUFACTURER);
		textModel.setText(Build.MODEL);

		// Check if your device support BlueTooth
		if (getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_BLUETOOTH)) {
			textSupportBT.setText("Support BLUETOOTH");
		} else {
			textSupportBT.setText("NOT Support BLUETOOTH");
		}

		// Check if your device support Bluetooth Low Energy
		if (getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_BLUETOOTH_LE)) {
			textSupportBTLE.setText("Support BLUETOOTH_LE");
		} else {
			textSupportBTLE.setText("NOT Support BLUETOOTH_LE");
		}

		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (bluetoothAdapter == null) {
			Toast.makeText(this,
					"Bluetooth is not supported on this hardware platform",
					Toast.LENGTH_LONG).show();
			finish();
			return;

		}

	}

	@Override
	protected void onStart() {
		super.onStart();

		// Turn ON BlueTooth if it is OFF
		if (!bluetoothAdapter.isEnabled()) {
			Intent enableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
		}
		setup();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_ENABLE_BT) {
			if (resultCode == RESULT_OK) {
				setup();

			} else {
				Toast.makeText(this, "BlueTooth NOT enabled",
						Toast.LENGTH_SHORT).show();
			}

		}

	}

	private void setup() {
		Set<BluetoothDevice> pairedDevices = bluetoothAdapter
				.getBondedDevices();

		if (pairedDevices.size() > 0) {
			pairedDeviceArrayList = new ArrayList<BluetoothDevice>();

			for (BluetoothDevice device : pairedDevices) {
				pairedDeviceArrayList.add(device);
			}

			pairedDeviceAdapter = new ArrayAdapter<BluetoothDevice>(this,
					android.R.layout.simple_list_item_1, pairedDeviceArrayList);

			listView.setAdapter(pairedDeviceAdapter);

			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					BluetoothDevice device = (BluetoothDevice) parent
							.getItemAtPosition(position);

					Toast.makeText(
							BluetoothLowEnergy.this,
							"Name: " + device.getName() + "\n" + "Address: "
									+ device.getAddress() + "\n"
									+ "BondState: " + device.getBondState()
									+ "\n" + "BluetoothClass: "
									+ device.getBluetoothClass() + "\n"
									+ "Class: " + device.getClass(),
							Toast.LENGTH_LONG).show();

				}
			});

		}

	}
}
