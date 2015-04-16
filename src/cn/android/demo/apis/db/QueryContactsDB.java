package cn.android.demo.apis.db;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

public class QueryContactsDB extends Activity {

	TextView textContacts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_android_query_contacts);
		textContacts = (TextView) findViewById(R.id.tv_query_contacts);

		Uri queryUri = ContactsContract.Contacts.CONTENT_URI;
		Log.v("DDD", "queryUri:" + queryUri.toString());
		String[] projection = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME };
		// 1、
		// String selection = ContactsContract.Contacts.DISPLAY_NAME
		// + " IS NOT NULL";
		// 如果您想查询只有电话号码，简单的变化选择联系人：

		// 2、
		// String selection = ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1";

		// 3、
		String constraint = "Lau";
		String selection = ContactsContract.Contacts.DISPLAY_NAME + " LIKE '%"
				+ constraint + "%'";
		
		CursorLoader cursorLoader = new CursorLoader(this, queryUri,
				projection, selection, null, null);

		Cursor cursor = cursorLoader.loadInBackground();

		int columnIndex_ID = cursor.getColumnIndex(Contacts._ID);
		int columnIndex_DISPLAYNAME = cursor
				.getColumnIndex(Contacts.DISPLAY_NAME);

		Log.v("DDD", "_ID:" + columnIndex_ID + "\n" + "_DISPLAYNAME:"
				+ columnIndex_DISPLAYNAME);

		String myContacts = "Contacts:\n";

		while (cursor.moveToNext()) {
			int id = cursor.getInt(columnIndex_ID);
			String displayName = cursor.getString(columnIndex_DISPLAYNAME);
			myContacts += String.valueOf(id) + " : " + displayName + "\n";
		}

		textContacts.setText(myContacts);
	}

}