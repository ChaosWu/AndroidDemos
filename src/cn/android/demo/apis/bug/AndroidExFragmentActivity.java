package cn.android.demo.apis.bug;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AndroidExFragmentActivity extends Activity  {
	static TextView status;
	static String myStatus = "";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_fragment_exfragment);
		status = (TextView) findViewById(R.id.tv_exfragment_status);
		updateStatus("onCreate()");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		updateStatus("onDestroy()");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		updateStatus("onPause()");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateStatus("onResume()");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		updateStatus("onStart()");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		updateStatus("onStop()");
	}


	public static void updateStatus(String st) {

		if (status == null) {
			myStatus += "delay - " + st + "\n";
		} else {
			myStatus += st + "\n";
			status.setText(myStatus);
		}
	}
}