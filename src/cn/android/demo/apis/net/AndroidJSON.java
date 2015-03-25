package cn.android.demo.apis.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AndroidJSON extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.net_android_json);
		TextView tvJsonSrc = (TextView) findViewById(R.id.json_src);
		TextView tvJsonObject = (TextView) findViewById(R.id.json_object);
		TextView tvJsonResult = (TextView) findViewById(R.id.json_result);
		TextView tvJsonMyBlog = (TextView) findViewById(R.id.json_myblog);
		TextView tvJsonMyUrl = (TextView) findViewById(R.id.json_myurl);
		TextView tvJsonMyArray = (TextView) findViewById(R.id.json_myarray);

		String json_source = getString(R.string.dummy_json);
		tvJsonSrc.setText(json_source);

		try {
			JSONObject jsonObject = new JSONObject(json_source);
			JSONObject results = jsonObject.getJSONObject("results");
			String myBlog = results.getString("myblog");
			String myUrl = results.getString("myurl");

			tvJsonObject.setText("\njsonObject:\n" + jsonObject.toString());
			tvJsonResult.setText("\nresults:\n" + results.toString());
			tvJsonMyBlog.setText("\nmyBlog:\n" + myBlog);
			tvJsonMyUrl.setText("\nmyUrl:\n" + myUrl);

			String strarrayElement = "\n";

			JSONArray arr = results.getJSONArray("array");
			for (int i = 0; i < arr.length(); i++) {
				JSONObject object = arr.getJSONObject(i);
				strarrayElement += object.getString("element") + "\n";
			}
			tvJsonMyArray.setText(strarrayElement);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
