package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ConfigUtil;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SDcard extends Activity implements OnClickListener {
	private Button btDowload;
	private Button btLoad;

	private String imageUrl = ConfigUtil.imageUrl;

	private String extStorageDirectory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_sdcard);

		btDowload = (Button) findViewById(R.id.bt_download_image);
		btLoad = (Button) findViewById(R.id.bt_load_image);

		btDowload.setOnClickListener(this);
		btLoad.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 下载图片 保存
		case R.id.bt_download_image:
			downloadImage();
			break;
		// 读取 显示图片
		case R.id.bt_load_image:
			loadImage();
			break;

		default:
			break;
		}
	}

	private void loadImage() {
		// TODO Auto-generated method stub

	}

	private void downloadImage() {
		// TODO Auto-generated method stub
		extStorageDirectory = Environment.getExternalStorageDirectory()
				.toString();
		btDowload.setText("Save to  " + extStorageDirectory
				+ "/"+System.currentTimeMillis() + ".png");

	}
}
