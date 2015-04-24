package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class AndroidWebView extends Activity {
	private WebView wv;

	private String mimeType = "text/html";
	private String encoding = "utf-8";

	private String htmlText = "<h1>It's in h1</h1><br/>"
			+ "<h2>It's in h2</h2><br/>" + "<i>Italics</i><br/>"
			+ "<b>Bold</b><br/>";

	// TODO 无法打开指定app 待思考
	// private final String url = "file:///android_asset/www/openAndroid.html";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_webview);
		wv = (WebView) findViewById(R.id.wv_open_app);
		// wv.loadUrl(url);
		wv.loadData(htmlText, mimeType, encoding);
	}
}
