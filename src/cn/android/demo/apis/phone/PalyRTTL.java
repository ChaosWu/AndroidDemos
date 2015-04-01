package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PalyRTTL extends Activity {
	MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_play_rtttl);
		Button play = (Button) findViewById(R.id.bt_play_rtttl);
		play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mediaPlayer != null) {
					mediaPlayer.release();
				}
				mediaPlayer = MediaPlayer.create(PalyRTTL.this, R.raw.a4);
				mediaPlayer.start();
			}
		});

	}
}
