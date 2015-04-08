package cn.android.demo.apis.ui.views;

import java.io.Externalizable;
import java.io.File;
import java.util.ArrayList;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class AndroidGallery extends Activity {
	Gallery gallery;
	GalleryBaseAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_view_gallery);
		gallery = (Gallery) findViewById(R.id.gallery_photo);

		adapter = new GalleryBaseAdapter(this);
		String path = Environment.getExternalStorageDirectory().getPath();

		String targetPath = path + "/DCIM/";

		ToastUtil.showToast(this, targetPath);
		File file = new File(targetPath);

		File[] files = file.listFiles();

		for (File f : files) {
			adapter.add(f.getPath());
		}

		gallery.setAdapter(adapter);

	}

	public class GalleryBaseAdapter extends BaseAdapter {
		ArrayList<String> galleryFileList;

		Context context;

		public GalleryBaseAdapter(Context context) {

			this.context = context;
			galleryFileList = new ArrayList<String>();

		}

		@Override
		public int getCount() {
			return galleryFileList.size();
		}

		@Override
		public Object getItem(int position) {
			return galleryFileList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Bitmap bm = BitmapFactory.decodeFile(galleryFileList.get(position));
			LinearLayout layout = new LinearLayout(context);

			layout.setLayoutParams(new Gallery.LayoutParams(250, 250));
			layout.setGravity(Gravity.CENTER);

			ImageView imageView = new ImageView(context);
			imageView.setLayoutParams(new Gallery.LayoutParams(200, 200));
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageView.setImageBitmap(bm);

			layout.addView(imageView);

			return layout;
		}

		public void add(String newItem) {
			galleryFileList.add(newItem);
		}

	}
}
