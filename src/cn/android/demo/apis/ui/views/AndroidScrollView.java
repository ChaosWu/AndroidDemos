package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;

public class AndroidScrollView extends Activity {
	private Button button1;
	private Button button2;
	private Button button3;

	private ScrollView scrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_scrollview);

		scrollView = (ScrollView) findViewById(R.id.sv_myview);
		button1 = (Button) findViewById(R.id.bt_scrolldown);
		button2 = (Button) findViewById(R.id.bt_scrolltotop);
		button3 = (Button) findViewById(R.id.bt_scrollup);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				scrollView.scrollBy(0, -120);
			}
		});
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				scrollView.scrollTo(0, 0);
			}
		});
		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				scrollView.scrollBy(0, 120);
			}
		});

	}
}
