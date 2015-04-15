package cn.android.demo.apis.project.mediastore;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListImage extends ListActivity {
	// 定义MediaStore.Images.Media ，内部或外部存储源
	Uri sourceUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	Uri thumbUri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;

	final String thumb_DATA = MediaStore.Images.Thumbnails.DATA;
	final String thumb_IMAGE_ID = MediaStore.Images.Thumbnails.IMAGE_ID;

//	SimpleCursorAdapter adapter;
	MyAdapter adapter;
	Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] from = { MediaStore.MediaColumns.TITLE };
		int[] to = { android.R.id.text1 };

		CursorLoader cursorLoader = new CursorLoader(this, sourceUri, null,
				null, null, MediaStore.Audio.Media.TITLE);

		cursor = cursorLoader.loadInBackground();
		adapter = new MyAdapter(this,
				android.R.layout.simple_list_item_multiple_choice, cursor,
				from, to,

				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

		// 设置
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		setListAdapter(adapter);

		getListView().setOnItemClickListener(clickListener);

	}

	OnItemClickListener clickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Cursor cursor = adapter.getCursor();
			cursor.moveToPosition(position);

			int int_id = cursor.getInt(cursor
					.getColumnIndex(MediaStore.Images.Media._ID));
			getThumbnail(int_id);

			// cursor.moveToFirst();
			//
			// String _id = cursor.getString(cursor
			// .getColumnIndex(MediaStore.Images.Media._ID));
			// Uri uri = Uri.withAppendedPath(sourceUri, _id);
			// ToastUtil.showToast(getApplicationContext(), uri.getPath());

		}
	};

	private Bitmap getThumbnail(int id) {

		String[] thumbColumns = { thumb_DATA, thumb_IMAGE_ID };

		CursorLoader thumbCursorLoader = new CursorLoader(this, thumbUri,
				thumbColumns, thumb_IMAGE_ID + "=" + id, null, null);

		Cursor thumbCursor = thumbCursorLoader.loadInBackground();

		Bitmap thumbBitmap = null;
		if (thumbCursor.moveToFirst()) {
			int thCulumnIndex = thumbCursor.getColumnIndex(thumb_DATA);

			String thumbPath = thumbCursor.getString(thCulumnIndex);

			Toast.makeText(getApplicationContext(), thumbPath,
					Toast.LENGTH_LONG).show();

			thumbBitmap = BitmapFactory.decodeFile(thumbPath);

			// Create a Dialog to display the thumbnail
			AlertDialog.Builder thumbDialog = new AlertDialog.Builder(
					ListImage.this);
			ImageView thumbView = new ImageView(ListImage.this);
			thumbView.setImageBitmap(thumbBitmap);
			LinearLayout layout = new LinearLayout(ListImage.this);
			layout.setOrientation(LinearLayout.VERTICAL);
			layout.addView(thumbView);
			thumbDialog.setView(layout);
			thumbDialog.show();

		} else {
			Toast.makeText(getApplicationContext(), "NO Thumbnail!",
					Toast.LENGTH_LONG).show();
		}

		return thumbBitmap;
	}

	public class MyAdapter extends SimpleCursorAdapter {

		Cursor myCursor;
		Context myContext;

		public MyAdapter(Context context, int layout, Cursor c, String[] from,
				int[] to, int flags) {
			super(context, layout, c, from, to, flags);

			myCursor = c;
			myContext = context;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			if (row == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.list_image_row, parent, false);
			}

			ImageView thumbV = (ImageView) row.findViewById(R.id.thumb);
			TextView textV = (TextView) row.findViewById(R.id.text);

			myCursor.moveToPosition(position);

			int myID = myCursor.getInt(myCursor
					.getColumnIndex(MediaStore.Images.Media._ID));
			String myData = myCursor.getString(myCursor
					.getColumnIndex(MediaStore.Images.Media.DATA));
			textV.setText(myData);

			String[] thumbColumns = { thumb_DATA, thumb_IMAGE_ID };
			CursorLoader thumbCursorLoader = new CursorLoader(myContext,
					thumbUri, thumbColumns, thumb_IMAGE_ID + "=" + myID, null,
					null);
			Cursor thumbCursor = thumbCursorLoader.loadInBackground();

			Bitmap myBitmap = null;
			if (thumbCursor.moveToFirst()) {
				int thCulumnIndex = thumbCursor.getColumnIndex(thumb_DATA);
				String thumbPath = thumbCursor.getString(thCulumnIndex);
				myBitmap = BitmapFactory.decodeFile(thumbPath);
				thumbV.setImageBitmap(myBitmap);
			}

			return row;
		}

	}
}