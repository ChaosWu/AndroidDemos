package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

public class AndroidPopupWindow extends Activity {
	Button open;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_popup_window);
		open = (Button) findViewById(R.id.bt_open_popup_window);
		open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openPopupWindow();
			}
		});

	}

	protected void openPopupWindow() {
		LayoutInflater inflater = (LayoutInflater) getBaseContext()
				.getSystemService(LAYOUT_INFLATER_SERVICE);

		View popupView = inflater.inflate(R.layout.ui_view_popup_window, null);
		
		final PopupWindow popupWindow=new PopupWindow(popupView, 400, 400, false);
		popupWindow.showAsDropDown(open, 50, -30);
		//设置点击焦点
		popupWindow.setFocusable(true);
		popupWindow.update();
	}
}
