package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class AndroidWebView extends Activity {
	private WebView wv;
	private Button bt;
	private String mimeType = "text/html";
	private String encoding = "utf-8";

	private String htmlText = "<h1>It's in h1</h1><br/>"
			+ "<h2>It's in h2</h2><br/>" + "<i>Italics</i><br/>"
			+ "<b>Bold</b><br/>"
			+ "<a href=javascript:android.StartWebView()>startWebView" + "</a>";

	// TODO 无法打开指定app 待思考
	// private final String url = "file:///android_asset/www/openAndroid.html";

	// private String urlPath = "http://whatsmyuseragent.com/";
	private String urlPath = "http://www.baidu.com/";
	private String ua = "Mozilla/5.0 (Android; Tablet; rv:20.0) Gecko/20.0 Firefox/20.0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_webview);
		wv = (WebView) findViewById(R.id.wv_open_app);
		wv.getSettings().setJavaScriptEnabled(true);

		wv.getSettings().setUserAgentString(ua);

		// 套的WebView是否应该启用对＆QUOT支持;视＆QUOT;HTML
		// meta标签还是应该使用宽视。当设置的值是假，布局宽度总是被设置为与设备无关的（CSS）的像素WebView控件的宽度。当值为true，并且页面包含视meta标签，在标签中指定的宽度值。如果页面不包含标签，或不提供宽，则视口宽将被使用。@参数使用是否启用视meta标签的支持
		wv.getSettings().setUseWideViewPort(true);

		// 设置是否在概述模式的WebView加载页面，也就是缩小了宽度以适应屏幕上的内容。此设置是考虑到当内容宽度大于所述web视图控件的宽度，例如，当{@link#getUseWideViewPort}被启用。默认值是假的。
		wv.getSettings().setLoadWithOverviewMode(true);

		wv.setWebViewClient(new MyWebViewClient());
		// wv.loadUrl(urlPath);
		//得翻墙
		wv.loadUrl("file:///android_asset/panoramiomap.html");

		bt = (Button) findViewById(R.id.bt_load_with_over_view_mode);
		// wv.loadUrl(url);
		// wv.loadData(htmlText, mimeType, encoding);

		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			wv.loadUrl(url);
			return true;
		}
	}
}
