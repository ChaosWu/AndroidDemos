package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class AndroidVideoView extends Activity {
	private VideoView videoView;
	// String viewSource = "rtsp://58.215.87.48/test1.sdp";
	// String viewSource =
	// "rtsp://218.204.223.237:554/live/1/0547424F573B085C/gsfp90ef4k0a6iap.sdp";
	String viewSource = "rtsp://211.154.7.106:3003/56.mp4";// 喜洋洋与灰太狼

	// String
	// viewSource="rtsp://211.139.194.251:554/live/2/13E6330A31193128/5iLd2iNl5nQ2s8r8.sdp";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_view_video);

		videoView = (VideoView) findViewById(R.id.vv_video_view);

		videoView.setVideoURI(Uri.parse(viewSource));
		videoView.setMediaController(new MediaController(this));
		videoView.requestFocus();
		videoView.start();
	}
}
