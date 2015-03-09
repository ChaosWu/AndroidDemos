package cn.android.demo.apis.app;

import java.io.IOException;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.app.WallpaperManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class AndroidWallpaper extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.app_android_wallpaper);

		Button btSetWallpaper = (Button) findViewById(R.id.bt_set_wallpaper);
		ImageView ivPreview = (ImageView) findViewById(R.id.im_preview_wallpaper);
		ivPreview.setImageResource(R.drawable.face_1);

		btSetWallpaper.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				WallpaperManager wallpaperManager = WallpaperManager
						.getInstance(getApplicationContext());
				
				
				try {
					wallpaperManager.setResource(R.drawable.face_1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});

	}
}
