package cn.android.demo.apis.appwidget;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class HelloWidgetProvider extends AppWidgetProvider {
	private SimpleDateFormat formatter = new SimpleDateFormat(
			"dd MMM yyyy  hh:mm:ss a");
	String strWidgetText = "";

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		// super.onDeleted(context, appWidgetIds);
		ToastUtil.showToast(context, "onDeleted()");
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		// super.onDisabled(context);
		ToastUtil.showToast(context, "onDisabled()");
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		// super.onEnabled(context);
		ToastUtil.showToast(context, "onEnabled()");
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub

		String currentTime = formatter.format(new Date());
		strWidgetText = strWidgetText + "\n" + currentTime;

		RemoteViews updateViews = new RemoteViews(context.getPackageName(),
				R.layout.app_hello_widget_layout);
		updateViews.setTextViewText(R.id.widgettext, strWidgetText);
		appWidgetManager.updateAppWidget(appWidgetIds, updateViews);

		super.onUpdate(context, appWidgetManager, appWidgetIds);
		ToastUtil.showToast(context, "onUpdate()");

	}
}