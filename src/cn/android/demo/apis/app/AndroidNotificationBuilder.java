package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 生成器的通知对象
 * 
 * @author Elroy
 * 
 */
//TODO 未完成
public class AndroidNotificationBuilder extends Activity {
	private static final int MY_NOTIFICATION_ID = 1;

	NotificationManager notificationManager;
	Notification notification;
	private final String 
	Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.app_android_notification_builder);
		bt = (Button) findViewById(R.id.bt_notification_builder_send);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}
}
