package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

/**
 * PopupMenu dome
 * 
 * @author Elroy
 * 
 */
@SuppressLint("NewApi")
public class AndroidPopupMenu extends Activity {
	Button show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_view_popup_menu);
		show = (Button) findViewById(R.id.bt_show_popup_menu);
		show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showPopupMenu(v);
			}
		});
	}

	protected void showPopupMenu(View v) {
		// 上下文，锚点
		PopupMenu menu = new PopupMenu(getApplicationContext(), v);
		menu.getMenuInflater().inflate(R.menu.popupmenu, menu.getMenu());
		menu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				ToastUtil.showToast(getApplicationContext(), item.toString());

				return true;
			}
		});
		menu.show();
	}
}
