package cn.android.demo.apis.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * json解析
 * 
 * @author Elroy
 * 
 */
public class JsonParseResult extends Activity {
	String search_url = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	String search_item = "android";
	String search_query = search_url + search_item;
	TextView textView;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_json_parse_result);
		textView = (TextView) findViewById(R.id.tv_json_parse_result);
		button = (Button) findViewById(R.id.bt_json_parse);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new JsonSearchTask().execute();
			}
		});
	}

	private class JsonSearchTask extends AsyncTask<Void, Void, Void> {
		String searchResult = "";

		@Override
		protected Void doInBackground(Void... params) {

			try {
				searchResult = parseResult(sendQuery(search_query));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			textView.setText(searchResult);
			super.onPostExecute(result);
		}
	}

	private String sendQuery(String query) throws IOException {
		String result = "";

		URL searchURL = new URL(query);

		HttpURLConnection connection = (HttpURLConnection) searchURL
				.openConnection();
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			InputStreamReader isr = new InputStreamReader(
					connection.getInputStream());
			BufferedReader br = new BufferedReader(isr, 1024 * 8);
			String line = null;

			while ((line = br.readLine()) != null) {
				result += line;

			}

			br.close();

		}

		return result;

	}

	/**
	 * 写法不是很好，最好用泛型模式 来写
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	private String parseResult(String json) throws JSONException {
		String parsedResult = "";

		JSONObject jo = new JSONObject(json);
		JSONObject jo_responseData = jo.getJSONObject("responseData");
		JSONArray jo_array_results = jo_responseData.getJSONArray("results");

		parsedResult += "Google Search APIs (JSON) for : " + search_item + "\n";
		parsedResult += "Number of results returned = "
				+ jo_array_results.length() + "\n\n";

		for (int i = 0; i < jo_array_results.length(); i++) {
			JSONObject jo_i = jo_array_results.getJSONObject(i);
			parsedResult += "title: " + jo_i.getString("title") + "\n";
			parsedResult += "content: " + jo_i.getString("content") + "\n";
			parsedResult += "url: " + jo_i.getString("url") + "\n";
			parsedResult += "\n";

		}

		return parsedResult;

	}
}
