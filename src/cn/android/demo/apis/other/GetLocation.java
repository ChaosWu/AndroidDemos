package cn.android.demo.apis.other;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * getLocationInWindow (int[] location):在计算其窗口视图的坐标。
 * 
 * 
 * getLocationOnScreen (int[] location):计算在屏幕上的视图的坐标。
 * 
 * 
 * @author Elroy
 * 
 */
public class GetLocation extends Activity {

	TextView textView1;
	TextView textView2;

	LinearLayout layout;
	ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_get_location);

		layout = (LinearLayout) findViewById(R.id.get_location_mainscreen);
		imageView = (ImageView) findViewById(R.id.get_location_object);
		textView1 = (TextView) findViewById(R.id.get_location_textview1);
		textView2 = (TextView) findViewById(R.id.get_location_textview2);

		readLocation(textView1, "onCreate");
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		readLocation(textView2, "onWindowFocusChanged");

	}

	private void readLocation(TextView tv, String status) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		int offsetX = displayMetrics.widthPixels - layout.getMeasuredWidth();
		int offsetY = displayMetrics.heightPixels - layout.getMeasuredHeight();

		int[] locationInWindow = new int[2];
		imageView.getLocationInWindow(locationInWindow);

		int[] locationOnScreen = new int[2];
		imageView.getLocationOnScreen(locationOnScreen);

		tv.setText("\n" + status + "\n" 
				+ "getLocationInWindow() - "
				+ locationInWindow[0] + " : " + locationInWindow[1] + "\n"
				
				+ "getLocationOnScreen() - " 
				+ locationOnScreen[0] + " : "+ locationOnScreen[1] + "\n" 
				
				+ "Offset x: y - " + offsetX+ " : " + offsetY);

	}

}
