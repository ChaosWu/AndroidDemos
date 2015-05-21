package cn.android.demo.apis.app;

import java.io.File;
import java.io.IOException;

import cn.android.demo.apis.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * TODO 有问题
 * 
 * @author Elroy
 * 
 */
public class AndroidRootSudo extends Activity {
	TextView tv;
	Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_android_root_su);
		bt = (Button) findViewById(R.id.root_su_bt);
		tv = (TextView) findViewById(R.id.root_su_info);

		tv.setText("是否具有权限：" + isRoot());
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getRoot();
				tv.setText("是否具有权限：" + isRoot());
			}
		});

	}

	/**
	 * 获取Root权限
	 */

	public void getRoot() {
		if (!isRoot()) {
			try {
				Runtime.getRuntime().exec("su");
			} catch (IOException e) {
				tv.setText("获取权限失败：" + e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断是否具有Root权限
	 * 
	 * @return
	 */
	public static boolean isRoot() {
		boolean b = false;
		if ((!new File("/system/bin/su").exists())
				&& (!new File("/system/xbin/su").exists())) {
			b = false;
		} else {
			b = true;
		}

		return b;
	}

}
