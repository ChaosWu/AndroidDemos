package cn.android.demo.apis.phone;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 位图保存方法
 * 
 * @author Elroy
 * 
 */
public class SaveBitmapStorage extends Activity {
	ImageView imageView;
	Button btnSaveExternalStorageDirectory;
	Button btnSaveMediaStore;
	Button btnSaveFileAndMediaStore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_save_bitmap_storage);
		imageView = (ImageView) findViewById(R.id.save_bitmap_storage_image);

		btnSaveExternalStorageDirectory = (Button) findViewById(R.id.save_bitmap_storage_saveExternalStorageDirectory);
		btnSaveExternalStorageDirectory
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						/*
						 * Save bitmap to ExternalStorageDirectory
						 */

						// get bitmap from ImageVIew
						// not always valid, depends on your drawable
						Bitmap bitmap = ((BitmapDrawable) imageView
								.getDrawable()).getBitmap();

						// always save as
						String fileName = "test.jpg";

						ByteArrayOutputStream bytes = new ByteArrayOutputStream();
						bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

						File ExternalStorageDirectory = Environment
								.getExternalStorageDirectory();
						File file = new File(ExternalStorageDirectory
								+ File.separator + fileName);

						FileOutputStream fileOutputStream = null;
						try {
							file.createNewFile();
							fileOutputStream = new FileOutputStream(file);
							fileOutputStream.write(bytes.toByteArray());

							Toast.makeText(SaveBitmapStorage.this,
									file.getAbsolutePath(), Toast.LENGTH_LONG)
									.show();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							if (fileOutputStream != null) {
								try {
									fileOutputStream.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}

					}
				});

		btnSaveMediaStore = (Button) findViewById(R.id.save_bitmap_storage_saveMediaStore);
		btnSaveMediaStore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * Save bitmap to MediaStore
				 */

				// get bitmap from ImageVIew
				// not always valid, depends on your drawable
				Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable())
						.getBitmap();

				ContentResolver cr = getContentResolver();
				String title = "myBitmap";
				String description = "My bitmap created by Android-er";
				String savedURL = MediaStore.Images.Media.insertImage(cr,
						bitmap, title, description);

				Toast.makeText(SaveBitmapStorage.this, savedURL,
						Toast.LENGTH_LONG).show();

			}
		});

		btnSaveFileAndMediaStore = (Button) findViewById(R.id.save_bitmap_storage_saveExternalStorageDirectoryMediaStore);
		btnSaveFileAndMediaStore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * Save bitmap to ExternalStorageDirectory
				 */

				// get bitmap from ImageVIew
				// not always valid, depends on your drawable
				Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable())
						.getBitmap();

				// always save as
				String fileName = "test.jpg";

				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

				File ExternalStorageDirectory = Environment
						.getExternalStorageDirectory();
				File file = new File(ExternalStorageDirectory + File.separator
						+ fileName);

				FileOutputStream fileOutputStream = null;
				try {
					file.createNewFile();
					fileOutputStream = new FileOutputStream(file);
					fileOutputStream.write(bytes.toByteArray());

					ContentResolver cr = getContentResolver();
					String imagePath = file.getAbsolutePath();
					String name = file.getName();
					String description = "My bitmap created by Android-er";
					String savedURL = MediaStore.Images.Media.insertImage(cr,
							imagePath, name, description);

					Toast.makeText(SaveBitmapStorage.this, savedURL,
							Toast.LENGTH_LONG).show();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (fileOutputStream != null) {
						try {
							fileOutputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		});
	}

}