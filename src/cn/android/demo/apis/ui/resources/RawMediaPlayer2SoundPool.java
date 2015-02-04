package cn.android.demo.apis.ui.resources;

import java.util.HashMap;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RawMediaPlayer2SoundPool extends Activity implements
		OnClickListener {
	MediaPlayer mediaPlayer;

	SoundPool soundPool;
	HashMap<Integer, Integer> soundPoolMap;
	int soundID = 1;

	Button play;
	Button pause;
	Button soundPlay;
	Button soundPause;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_resources_raw_media_play);

		initMediaPlay();
		initSoundPool();

	}

	private void initSoundPool() {

		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		soundPoolMap = new HashMap<Integer, Integer>();
		// soundPoolMap.put(soundID, soundPool.load(this, R.raw.midi_sound, 1));
		soundPoolMap.put(soundID, soundPool.load(this, R.raw.zhuadizhu, 1));

		soundPlay = (Button) findViewById(R.id.bt_soundpool_play);
		soundPause = (Button) findViewById(R.id.bt_soundpool_pause);

		soundPlay.setOnClickListener(this);
		soundPause.setOnClickListener(this);
	}

	private void initMediaPlay() {
		mediaPlayer = MediaPlayer.create(this, R.raw.zhuadizhu);
		play = (Button) findViewById(R.id.bt_play);
		pause = (Button) findViewById(R.id.bt_pause);

		play.setOnClickListener(this);
		pause.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_play:
			ToastUtil.showToast(this, "play Media");
			if (!mediaPlayer.isPlaying()) {
				mediaPlayer.start();
			}
			break;
		case R.id.bt_pause:
			ToastUtil.showToast(this, "pause Media");
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.pause();
			}
			break;
		case R.id.bt_soundpool_play:
			ToastUtil.showToast(this, "soundpool play");

			//
			// AudioManager am = (AudioManager)
			// getSystemService(Context.AUDIO_SERVICE);
			// float curVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
			// float maxVolume =
			// am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
			// float leftVolume = curVolume / maxVolume;
			// float rightVolume = curVolume / maxVolume;
			// soundPool.play(soundPoolMap.get(soundID), leftVolume,
			// rightVolume,
			// 1, 1, 1);

			AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			float curVolume = audioManager
					.getStreamVolume(AudioManager.STREAM_MUSIC);
			float maxVolume = audioManager
					.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
			float leftVolume = curVolume / maxVolume;
			float rightVolume = curVolume / maxVolume;
			int priority = 1;
			int no_loop = 0;
			float normal_playback_rate = 1f;
			soundPool.play(soundID, leftVolume, rightVolume, priority, no_loop,
					normal_playback_rate);

			break;
		case R.id.bt_soundpool_pause:
			ToastUtil.showToast(this, "soundpool pause");
			soundPool.pause(soundID);
			break;

		default:
			break;
		}
	}
}
