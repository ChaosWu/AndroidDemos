package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.TextView;

/**
 * V4 官方下拉刷新
 * 
 * @author Elroy
 * 
 */
public class AndroidSwipeRefreshLayout extends Activity {
	SwipeRefreshLayout swipeRefreshLayout;
	TextView tvInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_android_swipe_refresh_layout);
		tvInfo = (TextView) findViewById(R.id.swipe_refresh_layout_info);

		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
		swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN,
				Color.BLUE, Color.CYAN);
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				tvInfo.setText("WAIT: doing something!");
				// simulate doing something
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						swipeRefreshLayout.setRefreshing(false);
						tvInfo.setText("DONE");
					}

				}, 2000);

			}
		});

	}
}
