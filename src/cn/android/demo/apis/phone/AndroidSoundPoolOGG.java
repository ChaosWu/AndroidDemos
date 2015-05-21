package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 播放OGG音频文件
 * 
 * @author Elroy
 * 
 */
public class AndroidSoundPoolOGG extends Activity {
	Button button;
	AudioManager audioManager;
	SoundPool soundPool;
	int soundId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(layoutParams);

		button = new Button(this);
		button.setText("- PLAY OGG -");
		layout.addView(button);
		setContentView(layout);

		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		// the maximum number of simultaneous streams for this SoundPool object
		int maxStreams = 4;
		// the audio stream type as described in AudioManager
		int streamType = AudioManager.STREAM_MUSIC;
		// the sample-rate converter quality. Currently has no effect. Use 0 for
		// the default.
		int srcQuality = 0;

		soundPool = new SoundPool(maxStreams, streamType, srcQuality);
		soundId = soundPool.load(this, R.raw.shunshouqianyang, 1);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {

			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				if (status == 0) {
					button.setEnabled(true);

				} else {
					Toast.makeText(AndroidSoundPoolOGG.this,
							"SoundPool.load() fail", Toast.LENGTH_LONG).show();
				}
			}
		});
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				float vol = audioManager
						.getStreamVolume(AudioManager.STREAM_MUSIC);
				float maxVol = audioManager
						.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

				float leftVolume = vol / maxVol;
				float rightVolume = vol / maxVol;

				int priority = 1;
				int no_loop = 1;
				float normal_playback_rate = 1f;
				soundPool.play(soundId, leftVolume, rightVolume, priority,
						no_loop, normal_playback_rate);
			}
		});
	}
}
