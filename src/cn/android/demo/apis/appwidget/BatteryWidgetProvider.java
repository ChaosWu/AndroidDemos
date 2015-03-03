package cn.android.demo.apis.appwidget;

import org.apache.http.util.EncodingUtils;

import cn.android.demo.apis.R;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;

public class BatteryWidgetProvider extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		updateWidget(context);
		context.startService(new Intent(context, MyBatteryReceiver.class));
	}

	private void updateWidget(Context context) {
		RemoteViews updateViews = new RemoteViews(context.getPackageName(),
				R.layout.app_widget_layout);

		updateViews.setTextViewText(R.id.tv_app_widget, "waiting……");

		ComponentName componentName = new ComponentName(context,
				BatteryWidgetProvider.class);

		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);

		appWidgetManager.updateAppWidget(componentName, updateViews);
	}

	public class MyBatteryReceiver extends Service {
		private int mLevel = 0;
		private String mStatus = "";

		private BroadcastReceiver receiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
					mLevel = intent.getIntExtra("level", 0);
					int status = intent.getIntExtra("status",
							BatteryManager.BATTERY_STATUS_UNKNOWN);

					if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
						mStatus = "Charging";
					} else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
						mStatus = "Dis-charging";
					} else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
						mStatus = "Not charging";
					} else if (status == BatteryManager.BATTERY_STATUS_FULL) {
						mStatus = "Full";
					} else {
						mStatus = "";
					}
					updateAppWidget(context);

				}
			}

			private void updateAppWidget(Context context) {
				RemoteViews updateViews = new RemoteViews(
						context.getPackageName(), R.layout.app_widget_layout);

				updateViews.setTextViewText(R.id.tv_app_widget, "Level:"
						+ mLevel + "%\n" + "Status:" + mStatus);
				ComponentName componentName = new ComponentName(context,
						BatteryWidgetProvider.class);

				AppWidgetManager appWidgetManager = AppWidgetManager
						.getInstance(context);
				appWidgetManager.updateAppWidget(componentName, updateViews);

			}
		};

		@Override
		public IBinder onBind(Intent intent) {
			Log.v("DDD", System.currentTimeMillis() + "service 启动");
			return null;
		}

		@Override
		public void onCreate() {
			super.onCreate();

			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
			registerReceiver(receiver, intentFilter);
		}

		@Override
		public void onDestroy() {
			super.onDestroy();

			unregisterReceiver(receiver);
		}

	}
}
