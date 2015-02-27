package cn.android.demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {
	public static InputStream OpenHttpConnection(String strUrl) throws IOException {
		InputStream inputStream = null;
		URL url = new URL(strUrl);
		URLConnection connection = url.openConnection();

		HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.connect();

		if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			inputStream = httpURLConnection.getInputStream();
		}

		return inputStream;

	}
}
