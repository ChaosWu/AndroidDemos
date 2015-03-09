package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EMail extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_email);

		final EditText edittextEmailAddress = (EditText) findViewById(R.id.et_email_address);
		final EditText edittextEmailSubject = (EditText) findViewById(R.id.et_email_subject);
		final EditText edittextEmailText = (EditText) findViewById(R.id.et_email_text);

		Button buttonSendEmail_intent = (Button) findViewById(R.id.bt_sendemail_intent);
		buttonSendEmail_intent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String emailAddress = edittextEmailAddress.getText().toString();
				String emailSubject = edittextEmailSubject.getText().toString();
				String emailText = edittextEmailText.getText().toString();

				String emailAddressList[] = { emailAddress };

				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("plain/text");

				intent.putExtra(Intent.EXTRA_EMAIL, emailAddressList);
				intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
				intent.putExtra(Intent.EXTRA_TEXT, emailText);

				startActivity(Intent.createChooser(intent,
						"Choice App t send email:"));

			}
		});

	}
}
