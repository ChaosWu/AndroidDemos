package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 获取手机号码
 * 
 * @author Elroy
 * 
 */
public class GetPhoneNumberFromContactsDB extends Activity {
	Button button;
	TextView textView;
	final int RQS_PICKCONTACT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.phone_android_get_phone_number_from_contacts_db);
		button = (Button) findViewById(R.id.bt_get_phone_number_readcontact);
		textView = (TextView) findViewById(R.id.tv_get_phone_number_phone);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 常量联系人表，其中包含每生接触代表为同一人聚集的记录
				final Uri uriContact = ContactsContract.Contacts.CONTENT_URI;
				Intent intentPickContact = new Intent(Intent.ACTION_PICK,
						uriContact);
				startActivityForResult(intentPickContact, RQS_PICKCONTACT);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == RQS_PICKCONTACT) {
				Uri returnUri = data.getData();
				Cursor cursor = getContentResolver().query(returnUri, null,
						null, null, null);

				if (cursor.moveToNext()) {
					int columnIndex_ID = cursor
							.getColumnIndex(ContactsContract.Contacts._ID);
					String contactID = cursor.getString(columnIndex_ID);

					int columnIndex_HASPHONENUMBER = cursor
							.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
					String stringHasPhoneNumber = cursor
							.getString(columnIndex_HASPHONENUMBER);

					if (stringHasPhoneNumber.equalsIgnoreCase("1")) {
						Cursor cursorNum = getContentResolver()
								.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
										null,
										ContactsContract.CommonDataKinds.Phone.CONTACT_ID
												+ "=" + contactID, null, null);

						// Get the first phone number
						if (cursorNum.moveToNext()) {
							int columnIndex_number = cursorNum
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
							String stringNumber = cursorNum
									.getString(columnIndex_number);
							textView.setText(stringNumber);
						}

					} else {
						textView.setText("NO Phone Number");
					}

				} else {
					Toast.makeText(getApplicationContext(), "NO data!",
							Toast.LENGTH_LONG).show();
				}
			}
		}
	}

}