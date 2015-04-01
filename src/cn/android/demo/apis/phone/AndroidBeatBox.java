package cn.android.demo.apis.phone;

import java.util.Locale;
import java.util.Random;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 挺好玩的，用德语来BeatBox
 * 
 * @author Elroy
 * 
 */
public class AndroidBeatBox extends Activity implements OnInitListener {

	private final static int NUM_TEXT = 25;
	//private String[] beat = { "pv", "zk", "bschk", "kkkkkkkkkk " };

	String[] beat = { "pv ", "zk ", "bschk ", "kkkkkkkkkk " };
	private Random random;
	private TextToSpeech speech;

	private Button btRandomTv;
	private Button btPlay;
	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_beatbox);

		btRandomTv = (Button) findViewById(R.id.bt_beatbox_generaterandom);
		btPlay = (Button) findViewById(R.id.bt_beatbox_play);
		btPlay.setEnabled(false);
		et = (EditText) findViewById(R.id.et_beatbox);

		
		random = new Random();
		speech = new TextToSpeech(this, this);
		speech.setLanguage(Locale.GERMANY);

		btRandomTv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strBeat = "";
				for (int i = 0; i < NUM_TEXT; i++) {
					int randomInt = random.nextInt(beat.length);
					strBeat = strBeat + beat[randomInt] + " ";
				}
				et.setText(strBeat);
			}
		});
		btPlay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.v("DDD", et.getText().toString());
				speech.speak(et.getText().toString(), TextToSpeech.QUEUE_ADD,
						null);
			}
		});

	}

	@Override
	public void onInit(int status) {
		btPlay.setEnabled(true);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		speech.stop();
		speech.shutdown();

	}
}
