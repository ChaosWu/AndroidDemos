package cn.android.demo.apis.project.rss;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 	private String title = null;
	private String description = null;
	private String link = null;
	private String pubdate = null;
	private List<RSSItem> itemList;
 * 
 * 数据库结构
 * +---------------+---------------------+-------+
 * | Field         | Type                | Key   |
 * +---------------+---------------------+-------+
 * | id            |  INT                | PRI   |
 * | title         |  TEXT               |       |
 * | description   |  TEXT               |       |
 * | link          |  TEXT               |       |
 * | pubdate       |  TEXT               |       |
 * +---------------+---------------------+-------+
 * @author ElroyWu
 *
 */
public class RssDatabaseHandler extends SQLiteOpenHelper {
	// db version
	private static final int DB_VERSION = 1;
	// db name
	private static final String DB_NAME = "rss_reader";
	// contacts table name
	private static final String TABLE_RSS = "rss_table";
	// contacts table columns names
	private static final String KEY_ID = "id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_LINK = "link";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_PUBDATE = "pubdate";

	public RssDatabaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_RSS_TABLE = "CREATE TABLE " + TABLE_RSS + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_LINK
				+ " TEXT," + KEY_PUBDATE + " TEXT," + KEY_DESCRIPTION + " TEXT"
				+ ")";
		db.execSQL(CREATE_RSS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RSS);
		onCreate(db);
	}

	public void addFeed(RSSFeed feed) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, feed.getTitle());
		values.put(KEY_LINK, feed.getLink());
		values.put(KEY_PUBDATE, feed.getPubdate());
		values.put(KEY_DESCRIPTION, feed.getDescription());
		// 检查行 已经存在于数据库
		if (!isFeedExists(db, feed.getLink())) {
			db.insert(TABLE_RSS, null, values);
			db.close();
		} else {
			updateFeed(feed);
			db.close();
		}

	}

	// 更新单行行将通过RSS链接标识
	private int updateFeed(RSSFeed feed) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, feed.getTitle());
		values.put(KEY_LINK, feed.getLink());
		values.put(KEY_PUBDATE, feed.getPubdate());
		values.put(KEY_DESCRIPTION, feed.getDescription());

		int update = db.update(TABLE_RSS, values, KEY_LINK + " =?",
				new String[] { String.valueOf(feed.getLink()) });
		db.close();
		return update;
	}

	public boolean isFeedExists(SQLiteDatabase db, String link) {
		Cursor curos = db.rawQuery("SELECT 1 FROM " + TABLE_RSS
				+ " WHERE link= '" + link + "'", new String[] {});
		boolean exists = curos.getCount() > 0;
		return exists;
	}
}
