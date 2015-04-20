package cn.android.demo.apis.other;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OpenOtherApp extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.other_open_other_app);
		Button bt = (Button) findViewById(R.id.bt_open_other_app);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String packageName = "com.example.android.displayingbitmaps";
				String className = "com.example.android.displayingbitmaps.ui.ImageGridActivity";
				ApplicationInfo applicationInfo;
				try {
					applicationInfo = getPackageManager().getApplicationInfo(
							packageName, 0);

					Intent intent = new Intent(
							"android.intent.category.LAUNCHER");

					intent.setClassName(packageName, className);
					startActivity(intent);
				} catch (NameNotFoundException e) {
					e.printStackTrace();
					// TODO 处理程序未安装逻辑
				}
			}
		});

		Button bt1 = (Button) findViewById(R.id.bt_open_other_1);
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String LicenseInfo = "1111111111111111111222222222222222233333333333333333333333333333444444444444444444444444455555555555555555555555566666666666666666666666667777777777777777777777777888888888888888888888888888999999999999999999999999999";
				AlertDialog.Builder LicenseDialog = new AlertDialog.Builder(
						OpenOtherApp.this);
				LicenseDialog.setTitle("Legal Notices");
				LicenseDialog.setMessage(LicenseInfo);
				LicenseDialog.show();
			}
		});
		//回到桌面
		Button bt2 = (Button) findViewById(R.id.bt_open_other_2);
		bt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				startActivity(intent);
			}
		});

	}
}
