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

		public void updateById(int id, String v1, String v2) {
			ContentValues values = new ContentValues();
			values.put(KEY_CONTENT1, v1);
			values.put(KEY_CONTENT2, v2);
			sqLiteDatabase.update(MYDATABASE_TABLE, values, KEY_ID + "=" + id,
					null);
		}

		// 排序
		public Cursor queueAll_SortBy_CONTENT1() {
			String[] columns = new String[] { KEY_ID, KEY_CONTENT1,
					KEY_CONTENT2 };
			Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
					null, null, null, null, KEY_CONTENT1);

			return cursor;
		}

		public Cursor queueAll_SortBy_CONTENT2() {
			String[] columns = new String[] { KEY_ID, KEY_CONTENT1,
					KEY_CONTENT2 };
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

/*
 * 创建数据库 自定义一个辅助类继承SQLiteOpenHelper类.
 * 
 * onCreate(SQLiteDatabase db): 当数据库被创建的时候，能够生成表，并创建视图跟触发器。
 * onUpgrade(SQLiteDatabse db, int oldVersion, int newVersion):
 * 更新的时候可以删除表和创建新的表。
 */
class DatabaseHelperTest extends SQLiteOpenHelper {

	static final String dbName = "demoDB";
	static final String employeeTable = "Employees";
	static final String colID = "EmployeeID";
	static final String colName = "EmployeeName";
	static final String colAge = "Age";
	static final String colDept = "Dept";

	static final String deptTable = "Dept";
	static final String colDeptID = "DeptID";
	static final String colDeptName = "DeptName";

	static final String viewEmps = "ViewEmps";

	public DatabaseHelperTest(Context context) {
		super(context, dbName, null, 33);
	}

	// 创建库中的表，视图和触发器
	public void onCreate(SQLiteDatabase db) {  
      db.execSQL("CREATE TABLE "+deptTable+" ("+colDeptID+ " INTEGER PRIMARY KEY , "+  
        colDeptName+ " TEXT)");  
 
      db.execSQL("CREATE TABLE "+employeeTable+"("+colID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+  
            colName+" TEXT, "+colAge+" Integer, "+colDept+"INTEGER NOT NULL ,FOREIGN KEY ("+colDept+") REFERENCES "+deptTable+" ("+colDeptID+"));");  
 
      //创建触发器  
      db.execSQL("CREATE TRIGGER fk_empdept_deptid " +  
        " BEFORE INSERT "+  
        " ON "+employeeTable+  
        " FOR EACH ROW BEGIN"+  
        " SELECT CASE WHEN ((SELECT "+colDeptID+" FROM "+deptTable+" WHERE "+colDeptID+"=new."+colDept+" ) IS NULL)"+  
        " THEN RAISE (ABORT,'Foreign Key Violation') END;"+  
        "  END;");  
 
     //创建视图  
      db.execSQL("CREATE VIEW "+viewEmps+  
        " AS SELECT "+employeeTable+"."+colID+" AS _id,"+  
        " "+employeeTable+"."+colName+","+  
        " "+employeeTable+"."+colAge+","+  
        " "+deptTable+"."+colDeptName+""+  
        " FROM "+employeeTable+" JOIN "+deptTable+  
        " ON "+employeeTable+"."+colDept+" ="+deptTable+"."+colDeptID  
        );  
     }

	// 更新库中的表
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + employeeTable);
		db.execSQL("DROP TABLE IF EXISTS " + deptTable);

		db.execSQL("DROP TRIGGER IF EXISTS fk_empdept_deptid");
		db.execSQL("DROP VIEW IF EXISTS " + viewEmps);
		onCreate(db);
	}
	
}


//数据库使用方法
/*加入数据库*/
//	SQLiteDatabase db=this.getWritableDatabase();  
//	ContentValues cv=new ContentValues();  
//	   cv.put(colDeptID, 1);  
//	   cv.put(colDeptName, "Sales");  
//	   db.insert(deptTable, colDeptID, cv);  
//	 
//	   cv.put(colDeptID, 2);  
//	   cv.put(colDeptName, "IT");  
//	   db.insert(deptTable, colDeptID, cv);     
//	   db.close();  


/*更新数据*/
//public int UpdateEmp(Employee emp)  
//{  
// SQLiteDatabase db=this.getWritableDatabase();  
// ContentValues cv=new ContentValues();  
// cv.put(colName, emp.getName());  
// cv.put(colAge, emp.getAge());  
// cv.put(colDept, emp.getDept());  
// return db.update(employeeTable, cv, colID+"=?",   
//  new String []{String.valueOf(emp.getID())});     
//}  


/*删除数据*/
//public void DeleteEmp(Employee emp)  
//{  
// SQLiteDatabase db=this.getWritableDatabase();  
// db.delete(employeeTable,colID+"=?", new String [] {String.valueOf(emp.getID())});  
// db.close();  
//}  


/*取得所有部门信息*/
//Cursor getAllDepts()  
//{  
// SQLiteDatabase db=this.getReadableDatabase();  
// Cursor cur=db.rawQuery("SELECT "+colDeptID+" as _id,   
//  "+colDeptName+" from "+deptTable,new String [] {});  
//
// return cur;  
//}  

//取得部门内雇员信息
//public Cursor getEmpByDept(String Dept)  
//{  
// SQLiteDatabase db=this.getReadableDatabase();  
// String [] columns=new String[]{"_id",colName,colAge,colDeptName};  
// Cursor c=db.query(viewEmps, columns, colDeptName+"=?",   
//  new String[]{Dept}, null, null, null);  
// return c;  
//}  


//取得部门ID
//public int GetDeptID(String Dept)  
//{  
// SQLiteDatabase db=this.getReadableDatabase();  
// Cursor c=db.query(deptTable, new String[]{colDeptID+" as _id",colDeptName},  
//  colDeptName+"=?", new String[]{Dept}, null, null, null);  
// //Cursor c=db.rawQuery("SELECT "+colDeptID+" as _id FROM "+deptTable+"   
// //WHERE "+colDeptName+"=?", new String []{Dept});  
// c.moveToFirst();  
// return c.getInt(c.getColumnIndex("_id"));    
//}  