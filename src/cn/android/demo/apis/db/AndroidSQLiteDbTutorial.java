package cn.android.demo.apis.db;

import java.util.ArrayList;
import java.util.List;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Android Sqlite教程
 * 
 * @author ElroyWu
 * +--------------+------------------+--------+
 * | Field        |   Type           |Key     |     
 * +--------------+------------------+--------+
 * | id		      | INT				 |PRI	  | 
 * | name		  | TEXT			 | 	      |
 * | phone_number | TEXT			 | 	      |
 * +--------------+------------------+--------+
 * 
 * 
 */
public class AndroidSQLiteDbTutorial extends Activity {
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DBHelper db=new DBHelper(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_android_sqlite_tutorial);
		tv = (TextView) findViewById(R.id.db_sqlite_tutorial_tv);
		
		
		
		db.addContact(new Contact("赵云", "11111111111111111"));
		db.addContact(new Contact("张飞", "22222222222222"));
		db.addContact(new Contact("孟获", "3333333333333333"));
		db.addContact(new Contact("孙尚香", "44444444444444"));
		db.addContact(new Contact("刘备", "5555555555555555"));
		db.addContact(new Contact("诸葛亮", "66666666666666666"));
		db.addContact(new Contact("关羽", "7777777777777777"));
		db.addContact(new Contact("司马懿", "88888888888888888"));
		db.addContact(new Contact("郭嘉", "999999999999999999"));
		db.addContact(new Contact("典韦", "0000000000000000"));
		db.addContact(new Contact("卢布", "AAAAAAAAAA"));
		
		List<Contact> contacts=db.getAllContacts();
		for (Contact cn:contacts) {
			String info="Id:"+cn.getID()
					+" ,Name:"+cn.getName()
					+" ,Phone:"+cn.getPhoneNumber()
					+"\n";
			
			tv.append(info);
			
		}
	}

	public class Contact {

		// private variables
		int _id;
		String _name;
		String _phone_number;

		// Empty constructor
		public Contact() {

		}

		// constructor
		public Contact(int id, String name, String _phone_number) {
			this._id = id;
			this._name = name;
			this._phone_number = _phone_number;
		}

		// constructor
		public Contact(String name, String _phone_number) {
			this._name = name;
			this._phone_number = _phone_number;
		}

		// getting ID
		public int getID() {
			return this._id;
		}

		// setting id
		public void setID(int id) {
			this._id = id;
		}

		// getting name
		public String getName() {
			return this._name;
		}

		// setting name
		public void setName(String name) {
			this._name = name;
		}

		// getting phone number
		public String getPhoneNumber() {
			return this._phone_number;
		}

		// setting phone number
		public void setPhoneNumber(String phone_number) {
			this._phone_number = phone_number;
		}
	}

	public class DBHelper extends SQLiteOpenHelper {
		// 数据库版本
		private static final int DB_VERSION = 1;

		// 数据库名字
		private static final String DB_NAME = "Contacts_Manager";
		// 表名
		private static final String TABLE_CONTACTS = "contacts";

		private static final String KEY_ID = "id";
		private static final String KEY_NAME = "name";
		private static final String KEY_PH_NO = "phone_number";

		public DBHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS
					+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
					+ " TEXT," + KEY_PH_NO + " TEXT" + ")";
			db.execSQL(CREATE_CONTACTS_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// 如果存在
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

			onCreate(db);
		}

		/* 增删改查 */

		void addContact(Contact c) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(KEY_NAME, c.getName());
			values.put(KEY_PH_NO, c.getPhoneNumber());

			// 插入
			db.insert(TABLE_CONTACTS, null, values);
			db.close();
		}

		Contact getContact(int id) {
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db
					.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME,
							KEY_PH_NO }, KEY_ID + "=?",
							new String[] { String.valueOf(id) }, null, null,
							null, null);

			if (cursor != null) {
				cursor.moveToFirst();
			}
			Contact contact = new Contact(
					Integer.parseInt(cursor.getString(0)), cursor.getString(1),
					cursor.getString(2));

			return contact;
		}

//		public List<Contact> getAllContacts() {
//			List<Contact> list = new ArrayList<Contact>();
//
//			String selectQuery = "SELECT * FROM" + TABLE_CONTACTS;
//
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery(selectQuery, null);
//
//			if (cursor.moveToFirst()) {
//				do {
//					Contact contact = new Contact();
//					contact.setID(Integer.parseInt(cursor.getString(0)));
//					contact.setName(cursor.getString(1));
//					contact.setPhoneNumber(cursor.getString(2));
//
//					list.add(contact);
//				} while (cursor.moveToNext());
//			}
//			return list;
//		}
		
		 public List<Contact> getAllContacts() {
		        List<Contact> contactList = new ArrayList<Contact>();
		        // Select All Query
		        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
		 
		        SQLiteDatabase db = this.getWritableDatabase();
		        Cursor cursor = db.rawQuery(selectQuery, null);
		 
		        // looping through all rows and adding to list
		        if (cursor.moveToFirst()) {
		            do {
		                Contact contact = new Contact();
		                contact.setID(Integer.parseInt(cursor.getString(0)));
		                contact.setName(cursor.getString(1));
		                contact.setPhoneNumber(cursor.getString(2));
		                // Adding contact to list
		                contactList.add(contact);
		            } while (cursor.moveToNext());
		        }
		 
		        // return contact list
		        return contactList;
		    }

		public int updateContact(Contact contact) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(KEY_NAME, contact.getName());
			values.put(KEY_PH_NO, contact.getPhoneNumber());

			return db.update(TABLE_CONTACTS, values, KEY_ID + " =?",
					new String[] { String.valueOf(contact.getID()) });
		}

		// 删除单个联系人
		public void deleteContact(Contact contact) {
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_CONTACTS, KEY_ID + " =?",
					new String[] { String.valueOf(contact.getID()) });
			db.close();
		}

		// 获取联系人 个数
		public int getContactsCount() {
			String countQuery = "SELECT *FROM " + TABLE_CONTACTS;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			cursor.close();
			// 返回计数
			return cursor.getCount();
		}
	}
}
