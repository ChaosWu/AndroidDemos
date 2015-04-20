package cn.android.demo.apis.db;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class QueryContactsDB2 extends Activity {
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_android_query_contacts2);
		listView = (ListView) findViewById(R.id.lv_query_db_phone_2);

		Uri queryUri = ContactsContract.Contacts.CONTENT_URI;
		String[] projection = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.HAS_PHONE_NUMBER,
				ContactsContract.Contacts.LOOKUP_KEY };

		String selection = ContactsContract.Contacts.DISPLAY_NAME
				+ " IS NOT NULL";

		CursorLoader cursorLoader = new CursorLoader(this, queryUri,
				projection, selection, null, null);

		Cursor cursor = cursorLoader.loadInBackground();

		String[] from = { ContactsContract.Contacts.DISPLAY_NAME };
		int[] to = { android.R.id.text1 };

		ListAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor, from, to,
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(clickListener);

	}

	OnItemClickListener clickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Cursor cursor = (Cursor) parent.getItemAtPosition(position);
//			int item_ID = cursor.getInt(cursor
//					.getColumnIndex(ContactsContract.Contacts._ID));
//			String item_DisplayName = cursor.getString(cursor
//					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//			int item_HasPhoneNumber = cursor
//					.getInt(cursor
//							.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
			
			String item_LookUp = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));

			Uri lookUpUri = ContactsContract.Data.CONTENT_URI;
			String[] projection = new String[] {
					ContactsContract.CommonDataKinds.Phone.NUMBER,
					ContactsContract.CommonDataKinds.Phone.TYPE,
					ContactsContract.CommonDataKinds.Phone.LABEL };
			String selection = ContactsContract.Data.LOOKUP_KEY + "=?";

			String[] selectionArgs = new String[] { item_LookUp };

			CursorLoader cursorLoader_LookUp = new CursorLoader(
					QueryContactsDB2.this, lookUpUri, projection, selection,
					selectionArgs, null);
			Cursor cursor_LookUp = cursorLoader_LookUp.loadInBackground();

			int lookUpCol_Type = cursor_LookUp
					.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
			int lookUpCol_Number = cursor_LookUp
					.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
			int lookUpCol_Label = cursor_LookUp
					.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LABEL);
			String stringNums = item_LookUp + "\n";
			while (cursor_LookUp.moveToNext()) {
				int type = cursor_LookUp.getInt(lookUpCol_Type);

				String stringType;
				switch (type) {
				case ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM:
					// the actual type in LABEL
					stringType = "*" + cursor_LookUp.getString(lookUpCol_Label)
							+ "*";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
					stringType = "HOME";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
					stringType = "MOBILE";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
					stringType = "WORK";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK:
					stringType = "FAX_WORK";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME:
					stringType = "FAX_HOME";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_PAGER:
					stringType = "PAGER";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER:
					stringType = "OTHER";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_CALLBACK:
					stringType = "CALLBACK";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_CAR:
					stringType = "CAR";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_COMPANY_MAIN:
					stringType = "COMPANY_MAIN";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_ISDN:
					stringType = "ISDN";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_MAIN:
					stringType = "MAIN";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER_FAX:
					stringType = "OTHER_FAX";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_RADIO:
					stringType = "RADIO";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_TELEX:
					stringType = "TELEX";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_TTY_TDD:
					stringType = "TTY_TDD";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE:
					stringType = "WORK_MOBILE";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_WORK_PAGER:
					stringType = "WORK_PAGER";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_ASSISTANT:
					stringType = "ASSISTANT";
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_MMS:
					stringType = "MMS";
					break;
				default:
					stringType = "unknown";
					break;
				}

				String stringNumber = cursor_LookUp.getString(lookUpCol_Number);

				stringNums += String.valueOf(type) + " " + stringType + " : "
						+ stringNumber + "\n";
			}

			Toast.makeText(getApplicationContext(), stringNums,
					Toast.LENGTH_LONG).show();

		}

	};

}