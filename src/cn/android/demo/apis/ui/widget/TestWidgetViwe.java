package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class TestWidgetViwe extends Activity {
	private MyWidgetView myWidget_01;
	private MyWidgetView myWidget_02;
	private MyWidgetView myWidget_03;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_widget_test_widget_view);
		myWidget_01 = (MyWidgetView) findViewById(R.id.mywidget_01);

		myWidget_02 = (MyWidgetView) findViewById(R.id.mywidget_02);

		myWidget_03 = (MyWidgetView) findViewById(R.id.mywidget_03);

		myWidget_02.setColor(Color.RED);

		myWidget_03.setBackgroundColor(Color.GREEN);

		myWidget_03.setColor(Color.BLUE);

	}

}
