package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import cn.android.demo.apis.net.IP;
import cn.android.demo.utils.ToastUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager.LayoutParams;

@SuppressLint("NewApi")
public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		ToastUtil.showToast(context, "Alarm Received");
		KeyguardManager km = (KeyguardManager) context
				.getSystemService(Context.KEYGUARD_SERVICE);
		if (km.inKeyguardRestrictedInputMode()) {
//			((Activity) context).getWindow().addFlags(
//					LayoutParams.FLAG_SHOW_WHEN_LOCKED
//							| LayoutParams.FLAG_TURN_SCREEN_ON);
//			Intent intent1 = new Intent(context, IP.class);
//			context.startActivity(intent1);
//
//			Log.v("DDD", "锁屏");
		} else {
			buildNotification(context);
		}

	}

	private void buildNotification(Context context) {
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Builder builder = new Builder(context);

		Intent intent = new Intent(context, IP.class);

		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, 0);

		builder.setSmallIcon(R.drawable.chaoswu)
				.setContentTitle("ContentTitle").setContentText("ContentText")
				.setContentInfo("ContentInfo").setTicker("Ticker")
				.setLights(0xFFFF0000, 500, 500) // setLights (int argb, int
													// onMs, int offMs)
				.setContentIntent(pendingIntent).setAutoCancel(true);

		Notification notification = builder.getNotification();
		manager.notify(R.drawable.chaoswu, notification);
	}

}
