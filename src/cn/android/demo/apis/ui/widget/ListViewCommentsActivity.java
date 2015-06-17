package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.R;
import cn.android.demo.apis.R.id;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 模仿 萝莉炮 动画效果
 * @author Elroy
 * 
 * 步骤：
 * 1.取消Activity的动画效果
 * overridePendingTransition(0, 0);
 * 
 * 2.设置详情页的Activity的背景为透明，详情页动画的时候，还能看到原来的页面。设置Activity的Theme如下
 * 
 * <style name="AppTheme.CommentsActivity" parent="AppTheme">
 *  <item name="android:windowBackground">@android:color/transparent</item>
 *  <item name="android:windowIsTranslucent">true</item>
 * </style>
 * 
 * 3.ViewTreeObserver.OnPreDrawListener回调
 * 写出动画效果
 * 
 */
//interface  ViewTreeObserver.OnPreDrawListener
//当一个视图树将要绘制时，所要调用的回调函数的接口类

public class ListViewCommentsActivity extends Activity {

	protected static final String ARG_DRAWING_START_LOCATION = "arg_drawing_start_location";
	protected static final String NAME = "name";
	private int drawingStartLocation;
	private TextView tv;
	private String name;
	private LinearLayout root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_comments);
		tv = (TextView) findViewById(R.id.tv_listview_comments);
		root = (LinearLayout) findViewById(id.listview_comments_contentRoot);
		drawingStartLocation = getIntent().getIntExtra(
				ARG_DRAWING_START_LOCATION, 0);
		name = getIntent().getStringExtra(NAME);
		tv.setText("drawingStartLocation:" + drawingStartLocation + "/n"
				+ "name:" + name);

		if (savedInstanceState == null) {
			root.getViewTreeObserver().addOnPreDrawListener(
					new ViewTreeObserver.OnPreDrawListener() {

						@Override
						public boolean onPreDraw() {
							root.getViewTreeObserver().removeOnPreDrawListener(
									this);
							startIntroAnimation();
							return true;
						}
					});

		}

	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	protected void startIntroAnimation() {
		root.setScaleY(0.1f);
		root.setPivotY(drawingStartLocation);
		root.animate().scaleY(1).setDuration(1000)
				.setInterpolator(new AccelerateInterpolator())
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
					}
				}).start();
	}
}
