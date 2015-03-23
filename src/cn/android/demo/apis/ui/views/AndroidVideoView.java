package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

public class AndroidVideoView extends Activity {
	private VideoView videoView;
	private TextView textView;
	private Spinner spinner;

	private String url_1 = "http://us.sinaimg.cn/003iWlrLjx06QBVWGP1501040100lXnU0k01.mp4";
	private String url_2 = "http://us.sinaimg.cn/001N65zEjx06PA0XKpNd01040100G0pz0k01.mp4";

	private Button button;
	String rtspUrl[] = {
			"rtsp://211.154.7.106:3003/56.mp4",
			"rtsp://218.204.223.237:554/live/1/0547424F573B085C/gsfp90ef4k0a6iap.sdp",
			"rtsp://58.215.87.48/test1.sdp",
			"rtsp://211.139.194.251:554/live/2/13E6330A31193128/5iLd2iNl5nQ2s8r8.sdp",
			url_1, url_2

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_view_video);

		videoView = (VideoView) findViewById(R.id.vv_video_view);
		textView = (TextView) findViewById(R.id.tv_rtsp);
		spinner = (Spinner) findViewById(R.id.spinner_rtsp);
		button = (Button) findViewById(R.id.bt_show_dialog_rtsp);

		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_item, rtspUrl);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(adapter);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final SpannableString stMyWeb = new SpannableString(
						"http://www.baidu.com");
				Linkify.addLinks(stMyWeb, Linkify.ALL);

				AlertDialog alertDialog = new AlertDialog.Builder(
						AndroidVideoView.this)
						.setMessage(stMyWeb)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
									}
								}).create();
				alertDialog.show();
			}
		});
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				textView.setText("地址：" + rtspUrl[position]);

				Linkify.addLinks(textView, Linkify.EMAIL_ADDRESSES
						| Linkify.MAP_ADDRESSES | Linkify.PHONE_NUMBERS
						| Linkify.WEB_URLS);
				playVideo(position);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void playVideo(int position) {
		videoView.setVideoURI(Uri.parse(rtspUrl[position]));
		videoView.setMediaController(new MediaController(this));
		videoView.requestFocus();
		videoView.start();
	}
}
