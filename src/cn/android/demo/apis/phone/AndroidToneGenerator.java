package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;

/**
 * 按键声音
 * 
 * @author Elroy
 * 
 */
public class AndroidToneGenerator extends Activity {
	GridView gridView;
	static final String[] numbers = new String[] { "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "*", "0", "#" };
	static final int[] toneTypes = new int[] { ToneGenerator.TONE_DTMF_1,
			ToneGenerator.TONE_DTMF_2, ToneGenerator.TONE_DTMF_3,
			ToneGenerator.TONE_DTMF_4, ToneGenerator.TONE_DTMF_5,
			ToneGenerator.TONE_DTMF_6, ToneGenerator.TONE_DTMF_7,
			ToneGenerator.TONE_DTMF_8, ToneGenerator.TONE_DTMF_9,
			ToneGenerator.TONE_DTMF_S, // *
			ToneGenerator.TONE_DTMF_0, ToneGenerator.TONE_DTMF_P // #
	};

	static int streamType = AudioManager.STREAM_MUSIC;
	static int volume = 50;
	static int durationMs = 100;
	static final ToneGenerator toneGenerator = new ToneGenerator(streamType,
			volume);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_tonegenerator);

		gridView = (GridView) findViewById(R.id.phone_tonegenerator_gridView);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, numbers);

		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				toneGenerator.startTone(toneTypes[position], durationMs);
			}
		});

	}
}
