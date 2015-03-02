package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

public class AndroidVideoView extends Activity {
	private VideoView videoView;
	private TextView textView;
	private Spinner spinner;

	String rtspUrl[] = {
			"rtsp://211.154.7.106:3003/56.mp4",
			"rtsp://218.204.223.237:554/live/1/0547424F573B085C/gsfp90ef4k0a6iap.sdp",
			"rtsp://58.215.87.48/test1.sdp",
			"rtsp://211.139.194.251:554/live/2/13E6330A31193128/5iLd2iNl5nQ2s8r8.sdp" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_view_video);

		videoView = (VideoView) findViewById(R.id.vv_video_view);
		textView = (TextView) findViewById(R.id.tv_rtsp);
		spinner = (Spinner) findViewById(R.id.spinner_rtsp);

		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_item, rtspUrl);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				textView.setText("地址："+rtspUrl[position]);
				
				Linkify.addLinks(textView,  Linkify.EMAIL_ADDRESSES|
					      Linkify.MAP_ADDRESSES|
					      Linkify.PHONE_NUMBERS|
					      Linkify.WEB_URLS);
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
