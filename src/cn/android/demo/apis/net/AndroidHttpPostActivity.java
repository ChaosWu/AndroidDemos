package cn.android.demo.apis.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidHttpPostActivity extends Activity {

	TextView result;
	WebView wv;
	String webData;
	public Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			String str = (String) msg.obj;
			result.setText(str);
			webData = str;
			webData = webData.replace("#", "%23");
			webData = webData.replace("%", "%25");
			webData = webData.replace("\\", "%27");
			webData = webData.replace("?", "%3f");

			wv.loadData(webData, "text/html", "UTF-8");
		}

	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_android_http_post);
		result = (TextView) findViewById(R.id.tv_http_post_result);
		wv = (WebView) findViewById(R.id.wv_http_post);

		new Thread(new Runnable() {

			@Override
			public void run() {
				test();
			}
		}).start();
	}

	public void test() {

		BufferedReader bufferedReader = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost request = new HttpPost("http://search.yahoo.com/search");
		List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("p", "Android"));

		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
					postParameters);
			request.setEntity(entity);

			HttpResponse response = httpClient.execute(request);

			bufferedReader = new BufferedReader(new InputStreamReader(response
					.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String LineSeparator = System.getProperty("line.separator");
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line + LineSeparator);
			}
			bufferedReader.close();

			// result.setText(stringBuffer.toString());
			Message msg = new Message();
			msg.obj = stringBuffer.toString();
			handler.sendMessage(msg);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}