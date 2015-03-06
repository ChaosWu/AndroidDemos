package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SMS extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_sms);
		final EditText edittextSmsNumber = (EditText) findViewById(R.id.smsnumber);
		final EditText edittextSmsText = (EditText) findViewById(R.id.smstext);
		Button buttonSendSms = (Button) findViewById(R.id.sendsms);
		buttonSendSms.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SmsManager smsManager = SmsManager.getDefault();
				String smsNumber = edittextSmsNumber.getText().toString();
				String smsText = edittextSmsText.getText().toString();

				smsManager
						.sendTextMessage(smsNumber, null, smsText, null, null);
			}
		});
		// 该方法不需要添加权限
		Button btSendSmsIntent = (Button) findViewById(R.id.sendsms_intent);
		btSendSmsIntent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String smsNumber = edittextSmsNumber.getText().toString();
				String smsText = edittextSmsText.getText().toString();

				if (smsNumber.equals("") || smsNumber == null) {
					smsNumber = "无号码";
				}
				Uri uri = Uri.parse("smsto:" + smsNumber);
				Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
				intent.putExtra("sms_body", smsText);
				startActivity(intent);
			}
		});

	}
}
