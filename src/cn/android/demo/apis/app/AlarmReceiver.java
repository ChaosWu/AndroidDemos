package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import cn.android.demo.apis.net.IP;
import cn.android.demo.utils.ToastUtil;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.util.Log;

@SuppressLint("NewApi")
public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		ToastUtil.showToast(context, "Alarm Received");
		buildNotification(context);

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
