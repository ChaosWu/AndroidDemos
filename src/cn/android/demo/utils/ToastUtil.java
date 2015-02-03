package cn.android.demo.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	private static Toast sToast = null;

	private ToastUtil() {
	}

	public static void showToast(Context context, String msg) {
		if (sToast == null) {
			sToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);

		} else {
			sToast.setText(msg);
			sToast.setDuration(Toast.LENGTH_SHORT);
		}
		sToast.show();

	}
}
