package cn.android.demo.apis.other;

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
 * 获取联系人 手机号码
 * 
 * @author Elroy
 * 
 */
public class GetPhoneNumberFromContactsDatabase extends Activity {
	Button buttonReadContact;
	TextView textPhone;
	final int RQS_PICKCONTACT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.other_get_phone_number);
		buttonReadContact = (Button) findViewById(R.id.get_phone_number_readcontact);
		textPhone = (TextView) findViewById(R.id.get_phone_number_phone);

		buttonReadContact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Start activity to get contact
				final Uri uriContact = ContactsContract.Contacts.CONTENT_URI;
				Intent intentPickContact = new Intent(Intent.ACTION_PICK,
						uriContact);
				startActivityForResult(intentPickContact, RQS_PICKCONTACT);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
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
							textPhone.setText(stringNumber);
						}

					} else {
						textPhone.setText("NO Phone Number");
					}

				} else {
					Toast.makeText(getApplicationContext(), "NO data!",
							Toast.LENGTH_LONG).show();
				}
			}
		}
	}
}
