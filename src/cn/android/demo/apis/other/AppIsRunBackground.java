package cn.android.demo.apis.other;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Bundle;
import android.text.Spannable;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * 判断app是否运行在后台
 * 
 * @author Elroy
 * 
 */
public class AppIsRunBackground extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView tv = new TextView(this);
		tv.setMovementMethod(new ScrollingMovementMethod());

		setContentView(tv);

		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
		tv.setText("  ==判断程序是否运行后台==  ");
		for (RunningAppProcessInfo app : appProcesses) {
			String name = app.processName;
			if (app.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
				tv.append("\n程序--->>" + name + ": " +"后台" );
			} else if (app.importance == RunningAppProcessInfo.IMPORTANCE_EMPTY) {
				tv.append("\n程序--->>" + name + ": " + "空");
			} else if (app.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				tv.append("\n程序--->>" + name + ": " + "前台");
			} else if (app.importance == RunningAppProcessInfo.IMPORTANCE_PERCEPTIBLE) {
				tv.append("\n程序--->>" + name + ": " + "PERCEPTIBLE");
			} else if (app.importance == RunningAppProcessInfo.IMPORTANCE_SERVICE) {
				tv.append("\n程序--->>" + name + ": " + "service");
			} else if (app.importance == RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
				tv.append("\n程序--->>" + name + ": " + "VISIBLE");
			} else if (app.importance == RunningAppProcessInfo.REASON_PROVIDER_IN_USE) {
				tv.append("\n程序--->>" + name + ": " + "REASON_PROVIDER_IN_USE");
			} else if (app.importance == RunningAppProcessInfo.REASON_SERVICE_IN_USE) {
				tv.append("\n程序--->>" + name + ": " + "REASON_SERVICE_IN_USE");
			} else if (app.importance == RunningAppProcessInfo.REASON_UNKNOWN) {
				tv.append("\n程序--->>" + name + ": " + "REASON_UNKNOWN");
			} else if (app.importance == RunningAppProcessInfo.CONTENTS_FILE_DESCRIPTOR) {
				tv.append("\n程序--->>" + name + ": " + "CONTENTS_FILE_DESCRIPTOR");
			} else if (app.importance == RunningAppProcessInfo.PARCELABLE_WRITE_RETURN_VALUE) {
				tv.append("\n程序--->>" + name + ": " + "PARCELABLE_WRITE_RETURN_VALUE");
			}

		}
	}
}
