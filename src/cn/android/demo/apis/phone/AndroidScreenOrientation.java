package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AndroidScreenOrientation extends Activity {

	String msg = "Default without changed";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_screen_orientation);

		Button buttonSetPortrait = (Button) findViewById(R.id.setPortrait);
		Button buttonSetLandscape = (Button) findViewById(R.id.setLandscape);
		Button buttonSetUnspecified = (Button) findViewById(R.id.setUnspecified);
		Button buttonShowMsg = (Button) findViewById(R.id.showMsg);

		buttonSetPortrait.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			}
		});

		buttonSetLandscape.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			}
		});

		buttonSetUnspecified.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
			}
		});

		buttonShowMsg.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(AndroidScreenOrientation.this, "msg:\n" + msg,
						Toast.LENGTH_LONG).show();

			}
		});

		Toast.makeText(AndroidScreenOrientation.this, "onCreate\n" + msg,
				Toast.LENGTH_LONG).show();

		msg = "Changed!";
	}

}