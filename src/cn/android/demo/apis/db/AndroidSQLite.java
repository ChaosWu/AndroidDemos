package cn.android.demo.apis.db;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_android_sqlite);
		//tv = (TextView) findViewById(R.id.tv_contentlist);
		listView = (ListView) findViewById(R.id.lv_contentlist);

		
		editText1=(EditText) findViewById(R.id.et_db_content1);
		editText2=(EditText) findViewById(R.id.et_db_content2);
		
		add=(Button) findViewById(R.id.bt_db_add);
		delete=(Button) findViewById(R.id.bt_db_deleteall);
		
//		adapter = new SQLiteAdapter(this);
//		adapter.openToWrite();
//		adapter.deleteAll();
//		adapter.insert("小安全");
//		adapter.insert("小团队");
//		adapter.insert("小钱包");
//		adapter.insert("小电脑");
//		adapter.insert("小山寨");
//		adapter.insert("小中国");
//		adapter.insert("A for Apply");
//		adapter.insert("B for Boy");
//		adapter.insert("C for Cat");
//		adapter.insert("D for Dog");
//		adapter.insert("E for Egg");
//		adapter.insert("F for Fish");
//		adapter.insert("G for Girl");
//		adapter.insert("H for Hand");
//		adapter.insert("I for Ice-scream");
//		adapter.insert("J for Jet");
//		adapter.insert("K for Kite");
//		adapter.insert("L for Lamp");
//		adapter.insert("M for Man");
//		adapter.insert("N for Nose");
//		adapter.insert("O for Orange");
//		adapter.insert("P for Pen");
//		adapter.insert("Q for Queen");
//		adapter.insert("R for Rain");
//		adapter.insert("S for Sugar");
//		adapter.insert("T for Tree");
//		adapter.insert("U for Umbrella");
//		adapter.insert("V for Van");
//		adapter.insert("W for Water");
//		adapter.insert("X for X'mas");
//		adapter.insert("Y for Yellow");
//		adapter.insert("Z for Zoo");
//		adapter.close();
//
//		adapter = new SQLiteAdapter(this);
//		adapter.openToRead();
//		String read = adapter.queueAll();
//		tv.setText(read);
//		Cursor cursor = adapter.queueAll2();
//		startManagingCursor(cursor);
//		String[] from = new String[] { SQLiteAdapter.KEY_CONTENT };
//		int[] to = new int[] { R.id.tv_list_row };
//		SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
//				R.layout.listview_textview_item, cursor, from, to);
//		listView.setAdapter(cursorAdapter);
//
//		adapter.close();

	}

	public class SQLiteAdapter {
		public static final String DATABASE_NAME = "E_DATABASE";
		public static final String DATABASE_TABLE = "E_TABLE";
		public static final int DATABASE_VERSION = 1;
		public static final String KEY_CONTENT = "Content";
		public static final String KEY_ID = "_id";

		public static final String DATABASE_TABLE_1 = "E_TABLE";
		public static final String KEY_CONTENT1 = "Content1";
		public static final String KEY_CONTENT2 = "Content2";
		// create table MY_DATABASE (ID integer primary key, Content text not
		// null);
		private static final String SCRIPT_CREATE_DATABASE = "create table "
				+ DATABASE_TABLE + " (" + KEY_ID
				+ " integer primary key autoincrement, " + KEY_CONTENT
				+ " text not null);";

		private static final String SCRIPT_CREATE_DATABASE_1 = "create table "
				+ DATABASE_TABLE + " (" + KEY_ID
				+ "  integer primary key autoincrement, " + KEY_CONTENT1
				+ " text not null," + KEY_CONTENT2 + " text not null);";
		private SQLiteHelper sqLiteHelper;
		private SQLiteDatabase sqLiteDatabase;

		private Context context;

		public SQLiteAdapter(Context c) {
			this.context = c;
		}

		public SQLiteAdapter openToRead() {
			sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null,
					DATABASE_VERSION);
			sqLiteDatabase = sqLiteHelper.getReadableDatabase();

			return this;
		}

		public SQLiteAdapter openToWrite() {
			sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null,
					DATABASE_VERSION);
			sqLiteDatabase = sqLiteHelper.getWritableDatabase();
			return this;
		}

		public void close() {
			sqLiteHelper.close();
		}

		/** 增 */
		public long insert(String content) {
			ContentValues contentValues = new ContentValues();
			contentValues.put(KEY_CONTENT, content);

			return sqLiteDatabase.insert(DATABASE_TABLE, null, contentValues);
		}

		//
		public long insert(String content1, String content2) {
			ContentValues contentValues = new ContentValues();
			contentValues.put(KEY_CONTENT1, content1);
			contentValues.put(KEY_CONTENT2, content2);
			return sqLiteDatabase.insert(DATABASE_TABLE_1, null, contentValues);
		}

		/** 删 */
		public int deleteAll() {
			return sqLiteDatabase.delete(DATABASE_TABLE, null, null);
		}

		public int deleteAll_1() {
			return sqLiteDatabase.delete(DATABASE_TABLE_1, null, null);
		}

		/** 查 */

		public String queueAll() {
			String[] columns = new String[] { KEY_CONTENT };
			Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE, columns, null,
					null, null, null, null);
			String result = "";
			int index_content = cursor.getColumnIndex(KEY_CONTENT);
			for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor
					.moveToNext()) {
				result = result + cursor.getString(index_content) + "\n";
			}
			return result;

		}

		public Cursor queueAll2() {
			String[] columns = new String[] { KEY_ID, KEY_CONTENT };
			Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE, columns,
					null, null, null, null, null);
			return cursor;
		}

		public Cursor queueAll1() {
			String[] columns = new String[] { KEY_ID, KEY_CONTENT1,
					KEY_CONTENT2 };
			Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE_1, columns, null,
					null, null, null, null);
			return cursor;
		}

		public class SQLiteHelper extends SQLiteOpenHelper {

			public SQLiteHelper(Context context, String name,
					CursorFactory factory, int version) {
				super(context, name, factory, version);
				// TODO Auto-generated constructor stub
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				db.execSQL(SCRIPT_CREATE_DATABASE);
				db.execSQL(SCRIPT_CREATE_DATABASE_1);
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion,
					int newVersion) {

			}

		}
	}

}
