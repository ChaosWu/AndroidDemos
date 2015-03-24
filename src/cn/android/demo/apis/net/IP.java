package cn.android.demo.apis.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IP extends Activity {
	private TextView textView;
	private EditText editText;
	private Button button;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			textView.setText(msg.obj.toString());
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_android_ip);
		editText = (EditText) findViewById(R.id.et_ip_hostname);
		button = (Button) findViewById(R.id.bt_ip_check);
		textView = (TextView) findViewById(R.id.tv_inet_address);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String hostName = editText.getText().toString();
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							InetAddress[] hostInetAddress = InetAddress
									.getAllByName(hostName);
							String all = "";

							for (int i = 0; i < hostInetAddress.length; i++) {
								all = all + String.valueOf(i) + " : "
										+ hostInetAddress[i].toString() + "\n";

							}
							Message msg = new Message();
							msg.obj = all;
							handler.sendMessage(msg);

						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();

			}
		});

	}
}
