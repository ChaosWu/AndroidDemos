package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * 
 * @author Elroy
 * 
 */
public class AndroidWebView2 extends Activity {

	WebView mBrowser;
	EditText msg;
	Button sendMsg;

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}

	@SuppressLint("JavascriptInterface")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_android_webview2);

		mBrowser = $id(R.id.wv2_mybrowser);
		msg = $id(R.id.wv2_msg);
		sendMsg = $id(R.id.wv2_sendmsg);

		final MyJavaScriptInterface javaScriptInterface = new MyJavaScriptInterface(
				this);

		mBrowser.addJavascriptInterface(javaScriptInterface, "AndroidFunction");
		mBrowser.getSettings().setJavaScriptEnabled(true);
		mBrowser.loadUrl("file:///android_asset/mypage.html");

		sendMsg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strMsg = msg.getText().toString();
				mBrowser.loadUrl("javascript:callFromActivity(\"" + strMsg
						+ "\")");
			}
		});
	}

	public class MyJavaScriptInterface {
		Context context;

		public MyJavaScriptInterface(Context context) {
			this.context = context;
		}

		@JavascriptInterface
		public void showToast(final String toast) {
			// new Thread(new Runnable() {
			//
			// @Override
			// public void run() {
			ToastUtil.showToast(context, toast);

			// }
			// }).start();
		}

		@JavascriptInterface
		public void openAndroidDialog() {

			// new Thread(new Runnable() {
			//
			// @Override
			// public void run() {
			AlertDialog.Builder dialog = new AlertDialog.Builder(context);
			dialog.setTitle("DANGER!").setMessage("you can do what you want!")
					.setPositiveButton("ON", null).show();

			// }
			// }).start();

		}
	}
}
