package cn.android.demo.apis.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BroadCastActivity2SMS extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		setContentView(tv);
		Intent intent = getIntent();
		if (intent != null) {
			String address = intent.getStringExtra("sms_address");
			if (address != null) {
				tv.append("\n\n发件人：\n" + address);
				String bodyString = intent.getStringExtra("sms_body");
				if (bodyString != null) {
					tv.append("\n短信内容：\n" + bodyString);
				}
			}
		}

	}
}
