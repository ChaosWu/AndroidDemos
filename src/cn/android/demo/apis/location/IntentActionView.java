package cn.android.demo.apis.location;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IntentActionView extends Activity {
	private final static String TAG = IntentActionView.class.getSimpleName();
	private Button btGetLocation;
	private EditText etXPostion;
	private EditText etYPostion;

	private TextView tvResult;

	private String x;
	private String y;
	private String uriStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_google_map);

		btGetLocation = (Button) findViewById(R.id.bt_get_location);
		etXPostion = (EditText) findViewById(R.id.et_x_postion);
		etYPostion = (EditText) findViewById(R.id.et_y_postion);

		tvResult = (TextView) findViewById(R.id.tv_location_result);

		btGetLocation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				x = etXPostion.getText().toString();
				y = etYPostion.getText().toString();
				if (x.equals("") || x == null || y.equals("") || y == null) {
					return;
				}
				uriStr = String.format("geo:%d, %d", x, y);
				Log.v(TAG, "结果为：" + uriStr);

				// Uri uri = Uri.parse(uriStr);
				//
				// Intent intent = new Intent();
				// intent.setAction(Intent.ACTION_VIEW);
				// intent.setData(uri);
				// startActivity(intent);

			}
		});

	}
}
