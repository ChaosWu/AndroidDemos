package cn.android.demo.apis.app;

import cn.android.demo.apis.R;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * ActionBar 常用属性
 * 
 * SHOW_AS_ACTION_ALWAYS：始终显示这个项目作为一个操作栏的按钮。
 * 
 * SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW：此产品的动作视图折叠到一个正常的菜单项。
 * 
 * SHOW_AS_ACTION_IF_ROOM：显示这个项目作为一个操作栏按钮，如果系统决定有空间的。
 * 
 * SHOW_AS_ACTION_NEVER：从不显示这个项目作为一个操作栏的按钮。
 * 
 * SHOW_AS_ACTION_WITH_TEXT：当此产品在操作栏，总是即使它也有指定一个图标文字标签显示它。
 * @author Elroy
 * 
 */
@SuppressLint("NewApi")
public class AndroidActionBar extends Activity {
	private static final int BUTTON_SHOW = 1;
	private static final int BUTTON_HIDE = 2;
	Button button1;
	Button button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_android_actionbar);
		button1 = (Button) findViewById(R.id.bt_show_actionbar);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ActionBar actionBar = getActionBar();
				actionBar.show();
			}
		});
		button2 = (Button) findViewById(R.id.bt_hide_actionbar);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ActionBar actionBar = getActionBar();
				actionBar.hide();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// menu.add(0, 0, 0, "Action Item 0");

		MenuItem item = menu.add(0, 0, 0, "Action Item 0");
		item.setIcon(R.drawable.demo_no_data_photo);
		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM|MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		menu.add(0, 1, 1, "Action Item 1");
		menu.add(0, 2, 2, "Action Item 2");
		menu.add(0, 3, 3, "Action Item 3");
		menu.add(0, 4, 4, "Action Item 4");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case 0:
			Toast.makeText(AndroidActionBar.this, "Action Item 0 selected!",
					Toast.LENGTH_LONG).show();
			return true;
		case 1:
			Toast.makeText(AndroidActionBar.this, "Action Item 1 selected!",
					Toast.LENGTH_LONG).show();
			return true;
		case 2:
			Toast.makeText(AndroidActionBar.this, "Action Item 2 selected!",
					Toast.LENGTH_LONG).show();
			return true;
		case 3:
			Toast.makeText(AndroidActionBar.this, "Action Item 3 selected!",
					Toast.LENGTH_LONG).show();
			return true;
		case 4:
			Toast.makeText(AndroidActionBar.this, "Action Item 4 selected!",
					Toast.LENGTH_LONG).show();
			return true;
		default:
			return false;

		}

	}
}