package cn.android.demo.apis.other;

import java.util.Enumeration;
import java.util.Properties;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.pm.FeatureInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Android设备读取系统信息 这个例子
 * 
 * 说明了如何读取Android系统信息;如系统构建，系统性能，内存信息，
 * 显示，系统提供的功能和系统共享库的名称。
 * 
 * @author Elroy
 * 
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ReadSystemInfo extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ScrollView scrollView = new ScrollView(this);

		TextView textView = new TextView(this);
		scrollView.addView(textView);
		setContentView(scrollView);

		String strInfo = "Build.VERSION.SDK_INT: " + Build.VERSION.SDK_INT
				+ "\n" + "Build.BOARD: " + Build.BOARD + "\n"
				+ "Build.BOOTLOADER: " + Build.BOOTLOADER + "\n"
				+ "Build.BRAND: " + Build.BRAND + "\n" + "Build.DEVICE: "
				+ Build.DEVICE + "\n" + "Build.DISPLAY: " + Build.DISPLAY
				+ "\n" + "Build.FINGERPRINT: " + Build.FINGERPRINT + "\n"
				+ "Build.HARDWARE: " + Build.HARDWARE + "\n" + "Build.HOST: "
				+ Build.HOST + "\n" + "Build.ID: " + Build.ID + "\n"
				+ "Build.MANUFACTURER: " + Build.MANUFACTURER + "\n"
				+ "Build.MODEL: " + Build.MODEL + "\n" + "Build.PRODUCT: "
				+ Build.PRODUCT + "\n" + "Build.TAGS: " + Build.TAGS + "\n"
				+ "Build.TIME: " + Build.TIME + "\n" + "Build.TYPE: "
				+ Build.TYPE + "\n" + "Build.USER: " + Build.USER + "\n";

		Properties properties = System.getProperties();
		Enumeration<?> propertyNames = properties.propertyNames();
		while (propertyNames.hasMoreElements()) {
			String key = (String) propertyNames.nextElement();
			String value = properties.getProperty(key);
			strInfo += key + " : " + value + "\n";

		}

		strInfo += "\n - Memory Info - \n";
		MemoryInfo memoryInfo = new MemoryInfo();
		ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		activityManager.getMemoryInfo(memoryInfo);
		strInfo += "Total Memory : " + memoryInfo.totalMem + "\n"; // API Level
																	// 16
		strInfo += "Available Memory : " + memoryInfo.availMem + "\n";
		strInfo += "Threshold : " + memoryInfo.threshold + "\n";
		strInfo += "Low Memory : " + memoryInfo.lowMemory + "\n";

		strInfo += "\n - Display - \n";
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		strInfo += "widthPixels : " + metrics.widthPixels + "\n";
		strInfo += "heightPixels : " + metrics.heightPixels + "\n";
		strInfo += "xdpi : " + metrics.xdpi + "\n";
		strInfo += "ydpi : " + metrics.ydpi + "\n";
		strInfo += "density : " + metrics.density + "\n";
		strInfo += "densityDpi : " + metrics.densityDpi + "\n";
		strInfo += "scaledDensity : " + metrics.scaledDensity + "\n";

		strInfo += "\n - SystemAvailableFeatures - \n";
		FeatureInfo[] featureInfo = getPackageManager()
				.getSystemAvailableFeatures();
		for (int i = 0; i < featureInfo.length; i++) {
			strInfo += featureInfo[i].toString() + "\n";
		}

		strInfo += "\n - SystemSharedLibraryNames - \n";
		String[] systemSharedLibraryNames = getPackageManager()
				.getSystemSharedLibraryNames();
		for (int i = 0; i < systemSharedLibraryNames.length; i++) {
			strInfo += systemSharedLibraryNames[i] + "\n";
		}

		textView.setText(strInfo);
	}
}
