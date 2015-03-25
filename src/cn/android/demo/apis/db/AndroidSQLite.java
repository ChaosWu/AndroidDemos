package cn.android.demo.apis.db;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class AndroidSQLite extends Activity {
	private TextView tv;
	private ListView listView;
	private SQLiteAdapter adapter;

	private EditText editText1;
	private EditText editText2;
	private Button add;
	private Button delete;

	private Cursor cursor;
	private SimpleCursorAdapter cursorAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_android_sqlite);
		// tv = (TextView) findViewById(R.id.tv_contentlist);
		listView = (ListView) findViewById(R.id.lv_contentlist);

		editText1 = (EditText) findViewById(R.id.et_db_content1);
		editText2 = (EditText) findViewById(R.id.et_db_content2);

		add = (Button) findViewById(R.id.bt_db_add);
		delete = (Button) findViewById(R.id.bt_db_deleteall);

		adapter = new SQLiteAdapter(this);
		adapter.openToWrite();

		cursor = adapter.queueAll();

		String[] from = new String[] { SQLiteAdapter.KEY_ID,
				SQLiteAdapter.KEY_CONTENT1, SQLiteAdapter.KEY_CONTENT2 };

		int[] to = new int[] { R.id.id, R.id.text1, R.id.text2 };

		cursorAdapter = new SimpleCursorAdapter(this,
				R.layout.listview_db_tv_item, cursor, from, to);
		listView.setAdapter(cursorAdapter);

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String data1 = editText1.getText().toString();
				String data2 = editText2.getText().toString();

				adapter.insert(data1, data2);

				updateList();
			}
		});
		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				adapter.deleteAll();
				updateList();
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Cursor cursor = (Cursor) parent.getItemAtPosition(position);
				int itemId = cursor.getInt(cursor
						.getColumnIndex(SQLiteAdapter.KEY_ID));

				String itemContent1 = cursor.getString(cursor
						.getColumnIndex(SQLiteAdapter.KEY_CONTENT1));
				String itemContent2 = cursor.getString(cursor
						.getColumnIndex(SQLiteAdapter.KEY_CONTENT2));
				String item = String.valueOf(itemId) + ":" + itemContent1
						+ "\n" + itemContent2;
				ToastUtil.showToast(getBaseContext(), item);
				adapter.deleteById(itemId);
				cursor.requery();
			}
		});

		//
		// adapter = new SQLiteAdapter(this);
		// adapter.openToRead();
		// String read = adapter.queueAll();
		// tv.setText(read);
		// Cursor cursor = adapter.queueAll2();
		// startManagingCursor(cursor);
		// String[] from = new String[] { SQLiteAdapter.KEY_CONTENT };
		// int[] to = new int[] { R.id.tv_list_row };
		// SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
		// R.layout.listview_textview_item, cursor, from, to);
		// listView.setAdapter(cursorAdapter);
		//
		// adapter.close();

	}

	/** 更新listview */
	protected void updateList() {
		cursor.requery();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (adapter != null) {
			adapter.close();

		}
	}

	public class SQLiteAdapter {

		public static final String MYDATABASE_NAME = "MY_DATABASE";

		public static final String MYDATABASE_TABLE = "MY_TABLE";

		public static final int MYDATABASE_VERSION = 1;

		public static final String KEY_ID = "_id";

		public static final String KEY_CONTENT1 = "Content1";

		public static final String KEY_CONTENT2 = "Content2";

		// create table MY_DATABASE (ID integer primary key, Content text not
		// null);

		private static final String SCRIPT_CREATE_DATABASE =

		"create table " + MYDATABASE_TABLE + " ("

		+ KEY_ID + " integer primary key autoincrement, "

		+ KEY_CONTENT1 + " text not null, "

		+ KEY_CONTENT2 + " text not null);";

		private SQLiteHelper sqLiteHelper;

		private SQLiteDatabase sqLiteDatabase;

		private Context context;

		public SQLiteAdapter(Context c) {

			context = c;

		}

		public SQLiteAdapter openToRead() throws android.database.SQLException {

			sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
					MYDATABASE_VERSION);

			sqLiteDatabase = sqLiteHelper.getReadableDatabase();

			return this;

		}

		public SQLiteAdapter openToWrite() throws android.database.SQLException {

			sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,
					MYDATABASE_VERSION);

			sqLiteDatabase = sqLiteHelper.getWritableDatabase();

			return this;

		}

		public void close() {

			sqLiteHelper.close();

		}

		public long insert(String content1, String content2) {

			ContentValues contentValues = new ContentValues();

			contentValues.put(KEY_CONTENT1, content1);

			contentValues.put(KEY_CONTENT2, content2);

			return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);

		}

		public int deleteAll() {

			return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);

		}

		public void deleteById(int id) {
			sqLiteDatabase.delete(MYDATABASE_TABLE, KEY_ID + "=" + id, null);
		}

		public Cursor queueAll() {

			String[] columns = new String[] { KEY_ID, KEY_CONTENT1,
					KEY_CONTENT2 };

			Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,

			null, null, null, null, null);

			return cursor;

		}

		public void updateById(int id,String v1,String v2){
			ContentValues values=new ContentValues();
			values.put(KEY_CONTENT1, v1);
			values.put(KEY_CONTENT2, v2);
			sqLiteDatabase.update(MYDATABASE_TABLE, values, KEY_ID+"="+id, null);
		}
		//排序
		public Cursor queueAll_SortBy_CONTENT1(){
			  String[] columns = new String[]{KEY_ID, KEY_CONTENT1, KEY_CONTENT2};
			  Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns, 
			    null, null, null, null, KEY_CONTENT1);
			  
			  return cursor;
			 }
			 
			 public Cursor queueAll_SortBy_CONTENT2(){
			  String[] columns = new String[]{KEY_ID, KEY_CONTENT1, KEY_CONTENT2};
			  Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns, 
			    null, null, null, null, KEY_CONTENT2);
			  
			  return cursor;
			 }
		public class SQLiteHelper extends SQLiteOpenHelper {

			public SQLiteHelper(Context context, String name,

			CursorFactory factory, int version) {

				super(context, name, factory, version);

			}

			@Override
			public void onCreate(SQLiteDatabase db) {

				// TODO Auto-generated method stub

				db.execSQL(SCRIPT_CREATE_DATABASE);

			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion,
					int newVersion) {

				// TODO Auto-generated method stub

			}

		}

	}

	public class SQLiteHelper extends SQLiteOpenHelper {

		public SQLiteHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// db.execSQL(SCRIPT_CREATE_DATABASE);
			db.execSQL(SQLiteAdapter.SCRIPT_CREATE_DATABASE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

	}

}
