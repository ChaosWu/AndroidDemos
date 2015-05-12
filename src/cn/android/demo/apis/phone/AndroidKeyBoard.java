package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

/**
 * Android 键盘控制
 * 
 * @author Elroy
 * 
 */
public class AndroidKeyBoard extends Activity {
	private PopupWindow popWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_keyboard);
		Button button = (Button) findViewById(R.id.bt_show_hide_keyboard);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				hideShowKeyboard();
			}
		});

		Button button2 = (Button) findViewById(R.id.bt_close_keyboard);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				closeKeyboard();
			}
		});

		Button button3 = (Button) findViewById(R.id.bt_comments_keyboard);
		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				commentsKeyboard(v);
			}
		});

	}

	protected void commentsKeyboard(View v) {
		showPopup(v);
		popupInputMethodWindow();
	}

	private void popupInputMethodWindow() {
		// 弹出键盘 最好用异步方法
		InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	private void showPopup(View parent) {
		if (popWindow == null) {
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = layoutInflater.inflate(
					R.layout.phone_keyboard_popwindow_pinglun, null);
			// 创建一个PopuWidow对象
			popWindow = new PopupWindow(view,
					LinearLayout.LayoutParams.FILL_PARENT, 100, true);
		}
		// popupwindow弹出时的动画
		// popWindow.setAnimationStyle(R.style.popupWindowAnimation);
		// 使其聚集 ，要想监听菜单里控件的事件就必须要调用此方法
		popWindow.setFocusable(true);
		// 设置允许在外点击消失
		popWindow.setOutsideTouchable(false);
		// 设置背景，这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
		popWindow.setBackgroundDrawable(new BitmapDrawable());
		// 软键盘不会挡着popupwindow
		popWindow
				.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		// 设置菜单显示的位置
		popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		// 监听菜单的关闭事件
		popWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
			}
		});
		// 监听触屏事件
		popWindow.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	// 关闭或者显示
	private void hideShowKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			Log.v("DDD", "1");

			imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	// 关闭软键盘

	private void closeKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive() && getCurrentFocus() != null) {
			if (getCurrentFocus().getWindowToken() != null) {
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
						InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}

	}
}



