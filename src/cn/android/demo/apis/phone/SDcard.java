package cn.android.demo.apis.phone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.android.demo.apis.R;
import cn.android.demo.utils.BitmapUtil;
import cn.android.demo.utils.ConfigUtil;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SDcard extends Activity implements OnClickListener {
	private static final int PICKFILE_RESULT_CODE = 1;
	private Button btDowload;
	private Button btLoad;
	private Button btPick;

	private Button btSelectImage;
	private ImageView imageView;
	private ImageView imageThumbnail;
	private TextView textView;
	private TextView textView2;

	
	Bitmap bmThumbnail;
	private String imageUrl = ConfigUtil.imageHeadUrl;
	private String imageName = "MyHead.png";
	private String extStorageDirectory = Environment
			.getExternalStorageDirectory().toString();


	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			switch (msg.what) {
			case 0:
				textView.setText("下载完成");
				break;
			case 1:
				textView.setText("正在下载");
				break;
			case 2:
				imageView.setImageBitmap((Bitmap) msg.obj);
				break;
			case 3:
				imageView.setBackgroundResource(R.drawable.demo_no_data_photo);
				textView.setText("没有资源，请下载");
				break;

			default:
				break;
			}
		}

	};
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_sdcard);

		btDowload = (Button) findViewById(R.id.bt_download_image);
		btLoad = (Button) findViewById(R.id.bt_load_image);
		btPick = (Button) findViewById(R.id.bt_pick);

		btSelectImage = (Button) findViewById(R.id.bt_select_image);

		textView = (TextView) findViewById(R.id.tv_download_text);
		textView2 = (TextView) findViewById(R.id.tv_target_uri);
		imageView = (ImageView) findViewById(R.id.iv_load_dowload_image);
		imageThumbnail = (ImageView) findViewById(R.id.iv_load_thumbnail_image);
		imageView.setBackgroundResource(R.drawable.demo_no_data_photo);

		btDowload.setOnClickListener(this);
		btLoad.setOnClickListener(this);
		btSelectImage.setOnClickListener(this);
		btPick.setOnClickListener(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			Uri targetUri = data.getData();
			textView2.setText(targetUri.toString());
			String exifFilePath = getRealPathFromURI(targetUri);
			try {
				// 读取图片信息
				ExifInterface exifInterface = new ExifInterface(exifFilePath);
				String exif_DATETIME = exifInterface
						.getAttribute(ExifInterface.TAG_DATETIME);

				textView2.setText(exif_DATETIME);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Bitmap bitmap;
			try {
				bitmap = BitmapFactory.decodeStream(getContentResolver()
						.openInputStream(targetUri));
				imageView.setImageBitmap(bitmap);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// Uri转String
	private String getRealPathFromURI(Uri contentUri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(contentUri, proj, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_pick:
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("file/*");
			startActivityForResult(intent, PICKFILE_RESULT_CODE);
			break;
		// 保存
		case R.id.bt_download_image:
			new Thread(new Runnable() {

				@Override
				public void run() {
					saveSdCard();

				}
			}).start();

			break;
		// 读取 显示图片
		case R.id.bt_load_image:
			loadImage();
			break;
		// 选择图片
		case R.id.bt_select_image:
			selectImage();
			break;

		default:
			break;
		}
	}

	private void selectImage() {
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, 0);
	}

	/** 读取 显示图片 */
	private void loadImage() {
		// File file = new File(extStorageDirectory, imageName);
		String path = extStorageDirectory + "/" + imageName;
		Bitmap bmp = BitmapFactory.decodeFile(path);
		bmThumbnail = ThumbnailUtils.extractThumbnail(bmp, 50,50);
		imageThumbnail.setImageBitmap(bmThumbnail);
		if (bmp != null) {
			Message message = new Message();
			message.obj = bmp;
			message.what = 2;
			handler.dispatchMessage(message);

		} else {
			handler.sendEmptyMessage(3);
		}
	}

	/** 下载图片 并保存 */
	private void saveSdCard() {
		handler.sendEmptyMessage(1);
		BitmapFactory.Options bmOptions;
		bmOptions = new BitmapFactory.Options();
		bmOptions.inSampleSize = 1;
		bitmap = BitmapUtil.LoadImage(imageUrl, bmOptions);
		// 保存sd代码
		OutputStream outputStream = null;
		try {
			Log.v("DDD", extStorageDirectory);
			File file = new File(extStorageDirectory, imageName);

			outputStream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.sendEmptyMessage(0);
		}

	}
}
