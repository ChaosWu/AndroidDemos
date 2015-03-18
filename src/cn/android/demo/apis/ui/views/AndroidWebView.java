package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class AndroidWebView extends Activity {
	private WebView wv;
	private final String url = "file:///android_asset/www/openAndroid.html";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_webview);
		wv = (WebView) findViewById(R.id.wv_open_app);
		wv.loadUrl(url);
	}
}
