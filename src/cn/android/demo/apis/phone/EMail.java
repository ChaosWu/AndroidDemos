package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;

/**
 * 还得绑定邮箱，麻烦
 * 
 * @author Elroy
 * 
 */
public class EMail extends Activity {
	static final int RQS_LOADIMAGE = 0;
	static final int RQS_SENDEMAIL = 1;
	Uri imageUri = null;
	TextView tvPath;
	ShareActionProvider myShareActionProvider;
	EditText edittextEmailAddress;
	EditText edittextEmailSubject;
	EditText edittextEmailText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_email);

		edittextEmailAddress = (EditText) findViewById(R.id.et_email_address);
		edittextEmailSubject = (EditText) findViewById(R.id.et_email_subject);
		edittextEmailText = (EditText) findViewById(R.id.et_email_text);

		tvPath = (TextView) findViewById(R.id.tv_email_imagepath);

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

				if (imageUri != null) {
					intent.putExtra(Intent.EXTRA_STREAM, imageUri);
					intent.setType("image/png");
				} else {
					intent.setType("plain/text");
				}

				startActivity(Intent.createChooser(intent,
						"Choice App t send email:"));

			}
		});

		Button buttonSelectImage = (Button) findViewById(R.id.bt_email_selectimage);
		buttonSelectImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, RQS_LOADIMAGE);
			}
		});
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_share, menu);

		MenuItem item = menu.findItem(R.id.menu_item_share);
		myShareActionProvider = (ShareActionProvider) item.getActionProvider();
		myShareActionProvider
				.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
		myShareActionProvider.setShareIntent(createShareIntent());
		return true;
	}

	private Intent createShareIntent() {
		String emailAddress = edittextEmailAddress.getText().toString();
		String emailSubject = edittextEmailSubject.getText().toString();
		String emailText = edittextEmailText.getText().toString();
		String emailAddressList[] = { emailAddress };

		Intent shareIntent = new Intent(Intent.ACTION_SEND);

		shareIntent.putExtra(Intent.EXTRA_EMAIL, emailAddressList);
		shareIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
		shareIntent.putExtra(Intent.EXTRA_TEXT, emailText);

		if (imageUri != null) {
			shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
			shareIntent.setType("image/png");
		} else {
			shareIntent.setType("plain/text");
		}

		return shareIntent;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case RQS_LOADIMAGE:
				imageUri = data.getData();
				tvPath.setText(imageUri.toString());
				break;
			case RQS_SENDEMAIL:

				break;

			default:
				break;
			}

		}

	}
}
