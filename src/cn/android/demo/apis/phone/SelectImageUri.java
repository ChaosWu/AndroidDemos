package cn.android.demo.apis.phone;

import java.io.File;
import java.io.FileNotFoundException;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectImageUri extends Activity {
	Button btLoadImage;
	TextView tvTargetUri;
	TextView tvTargetPath1;
	TextView tvTargetPath2;

	ImageView selectImage;

	Uri orgUri;
	String convertedPath;
	Uri uriFromPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.phone_android_select_image_uri);

		btLoadImage = (Button) findViewById(R.id.bt_uri_loadimage);
		tvTargetUri = (TextView) findViewById(R.id.tv_uri_target);
		tvTargetPath1 = (TextView) findViewById(R.id.tv_uri_targetpath1);
		tvTargetPath2 = (TextView) findViewById(R.id.tv_uri_targetpath2);

		selectImage = (ImageView) findViewById(R.id.iv_select_image);

		tvTargetUri.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectImage.setImageBitmap(null);
				try {
					Bitmap bm = BitmapFactory.decodeStream(getContentResolver()
							.openInputStream(orgUri));
					selectImage.setImageBitmap(bm);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		tvTargetPath1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectImage.setImageBitmap(null);
				Bitmap bm = BitmapFactory.decodeFile(convertedPath);
				selectImage.setImageBitmap(bm);

			}
		});

		tvTargetPath2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectImage.setImageBitmap(null);
				try {
					Bitmap bm = BitmapFactory.decodeStream(getContentResolver()
							.openInputStream(uriFromPath));
					selectImage.setImageBitmap(bm);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		btLoadImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent(
				// Intent.ACTION_PICK,
				// android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				// 或者
				Intent intent = new Intent(Intent.ACTION_PICK);
				Uri data = Uri.fromFile(Environment
						.getExternalStorageDirectory());
				String type = "image/*";
				intent.setDataAndType(data, type);
				startActivityForResult(intent, 0);

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			Uri targetUri = data.getData();
			orgUri = targetUri;
			tvTargetUri.setText("Uri:" + orgUri.toString());

			convertedPath = getPathFromUri_managedQuery(orgUri);
			tvTargetPath1.setText("Paht1 :" + convertedPath);

			uriFromPath = Uri.fromFile(new File(convertedPath));
			tvTargetPath2.setText("Paht2 :" + uriFromPath.toString());

		}
	}

	private String getPathFromUri_CursorLoader(Uri targetUri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		CursorLoader cursorLoader = new CursorLoader(getApplicationContext(),
				targetUri, projection, null, null, null);
		Cursor cursor = cursorLoader.loadInBackground();

		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

		cursor.moveToFirst();

		return cursor.getString(column_index);
	}

	private String getPathFromUri_managedQuery(Uri targetUri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		// Cursor cursor = managedQuery(targetUri, projection, null, null,
		// null);
		Cursor cursor2 = getContentResolver().query(targetUri, projection,
				null, null, null);

		int column_index = cursor2
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor2.moveToFirst();

		return cursor2.getString(column_index);
	}

}
