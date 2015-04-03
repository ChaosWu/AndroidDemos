package cn.android.demo.apis.phone;

import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.app.ListActivity;
import android.app.DownloadManager.Query;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class AndroidListMediaActivity extends ListActivity {
	ListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		String[] from = { MediaStore.MediaColumns.TITLE };
		int[] to = { android.R.id.text1 };
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				MediaStore.Audio.Media.TITLE);

		  adapter = new android.support.v4.widget.SimpleCursorAdapter(
				this, android.R.layout.simple_list_item_1, cursor, from, to);

		setListAdapter(adapter);

	}
	
//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		Cursor cursor = ((CursorAdapter) adapter).getCursor();
//		cursor.moveToPosition(position);
//		
//		String _id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
//		  String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
//		  String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
//		  String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
//		  int duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
//
//		  String info = "_ID: " + _id + "\n"
//		     + "TITLE: " + title + "\n"
//		     + "ARTIST: " + artist + "\n"
//		     + "ALBUM: " + album + "\n"
//		     + "DURATION: " + duration/1000 + "s";
//		  ToastUtil.showToast(getApplicationContext(), info);
//	}
	
}
