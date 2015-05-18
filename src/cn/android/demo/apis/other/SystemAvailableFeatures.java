package cn.android.demo.apis.other;

import android.app.Activity;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * 系统功能
 * 
 * @author Elroy
 * 
 */
public class SystemAvailableFeatures extends Activity {
	static PackageManager packageManager;
	static TextView info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		packageManager = getPackageManager();
		info = new TextView(this);
		// info.setMovementMethod(new ScrollingMovementMethod());

		ScrollView scrollView = new ScrollView(this);
		scrollView.addView(info);
		setContentView(scrollView);

		FeatureInfo[] featureInfos = packageManager
				.getSystemAvailableFeatures();

		if (featureInfos == null) {
			info.setText("NO feature available on the system!!!");
		} else {
			String strInfo = "";

			for (FeatureInfo info : featureInfos) {
				strInfo += info.name + "\n";
				strInfo += info.toString() + "\n\n";

			}
			info.setText(strInfo);
		}

		info.append("\n\n\n");

		String str2 = "";
		str2 += "has System Feature - BLUETOOTH: "
				+ packageManager
						.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)
				+ "\n";
		str2 += "has System Feature - BLUETOOTH_LE: "
				+ packageManager
						.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
				+ "\n";
		str2 += "has System Feature - CAMERA_ANY: "
				+ packageManager
						.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
				+ "\n";
		str2 += "has System Feature - CAMERA: "
				+ packageManager
						.hasSystemFeature(PackageManager.FEATURE_CAMERA) + "\n";
		str2 += "has System Feature - CAMERA_AUTOFOCUS: "
				+ packageManager
						.hasSystemFeature(PackageManager.FEATURE_CAMERA_AUTOFOCUS)
				+ "\n";
		str2 += "has System Feature - NFC: "
				+ packageManager.hasSystemFeature(PackageManager.FEATURE_NFC)
				+ "\n";
		str2 += "has System Feature - USB_ACCESSORY: "
				+ packageManager
						.hasSystemFeature(PackageManager.FEATURE_USB_ACCESSORY)
				+ "\n";
		str2 += "has System Feature - USB_HOST: "
				+ packageManager
						.hasSystemFeature(PackageManager.FEATURE_USB_HOST)
				+ "\n";
		str2 += "has System Feature - WIFI: "
				+ packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI)
				+ "\n";
		str2 += "has System Feature - WIFI_DIRECT: "
				+ packageManager
						.hasSystemFeature(PackageManager.FEATURE_WIFI_DIRECT)
				+ "\n";
		info.append(str2);

	}
}
