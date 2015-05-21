package cn.android.demo.apis.ui.views;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class AndroidWebView3 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WebView wv = new WebView(this);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.loadUrl("file:///android_asset/display_svg_using_html_js.html");
		setContentView(wv);
	}
}
