package cn.android.demo.apis.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
 * 	 FlickrQuery = FlickrQuery_url
 * + FlickrQuery_per_page
 * + FlickrQuery_nojsoncallback
 * + FlickrQuery_format
 * + FlickrQuery_tag + q
 * + FlickrQuery_key + FlickrApiKey
 */

public class AndroidFlickrActivity extends Activity {
	// String FlickrQuery_url =
	// "http://api.flickr.com/services/rest/?method=flickr.photos.search";
	// String FlickrQuery_per_page = "&per_page=1";
	// String FlickrQuery_nojsoncallback = "&nojsoncallback=1";
	// String FlickrQuery_format = "&format=json";
	// String FlickrQuery_tag = "&tags=";
	// String FlickrQuery_key = "&api_key=";
	// String FlickrApiKey = "2155e9406043b7494453105eec99ae37";

	EditText searchText;
	Button searchButton;
	TextView textQueryResult;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			String obj = (String) msg.obj;
			textQueryResult.setText(obj);
			textQueryResult.setMovementMethod(new ScrollingMovementMethod());
			
			try {
				JSONObject jsonObject=new JSONObject(obj);
				String url=jsonObject.getString("picture");
				Log.v("DDD", "图片url:"+url);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_android_flickr);
		searchText = (EditText) findViewById(R.id.searchtext);
		searchButton = (Button) findViewById(R.id.searchbutton);
		textQueryResult = (TextView) findViewById(R.id.queryresult);
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String searchQ = searchText.getText().toString();
				new Thread(new Runnable() {

					@Override
					public void run() {

						String searchResult = QueryFlickr(searchQ);
						Message msg = new Message();
						msg.obj = searchResult;
						handler.sendMessage(msg);
					}
				}).start();
			}
		});

	}
	private Bitmap loadPhoto(String url){
		return null;
	}

	private String QueryFlickr(String q) {
		String qResult = null;
		// String qString = FlickrQuery_url + FlickrQuery_per_page
		// + FlickrQuery_nojsoncallback + FlickrQuery_format
		// + FlickrQuery_tag + q + FlickrQuery_key + FlickrApiKey;
		//

		String qString = "http://open.iciba.com/dsapi";

		// String qString ="http://172.16.19.77:9091/api/TrainUser/get";

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(qString);

		try {
			HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();

			if (httpEntity != null) {
				InputStream inputStream = httpEntity.getContent();
				Reader in = new InputStreamReader(inputStream);
				BufferedReader bufferedreader = new BufferedReader(in);
				StringBuilder stringBuilder = new StringBuilder();

				String stringReadLine = null;

				while ((stringReadLine = bufferedreader.readLine()) != null) {
					stringBuilder.append(stringReadLine + "\n");
				}
				qResult = stringBuilder.toString();

			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return qResult;
	}
}
