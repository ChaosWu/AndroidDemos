package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import cn.android.demo.apis.ui.widget.VisualizerView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * 按键声音
 * 
 * @author Elroy
 * 
 */
@SuppressLint("NewApi")
public class AndroidToneGenerator extends Activity {
	GridView gridView;
	VisualizerView visualizerView;

	private Visualizer mVisualizer;
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
		visualizerView = (VisualizerView) findViewById(R.id.myvisualizerview);
		gridView = (GridView) findViewById(R.id.phone_tonegenerator_gridView);
		
		initAudio();
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

	private void initAudio() {
		streamType = AudioManager.STREAM_MUSIC;
		volume = 50;
		durationMs = 500;

		setupVisualizerFxAndUI();
		mVisualizer.setEnabled(true);

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (isFinishing()) {
			mVisualizer.release();
		}
	}

	private void setupVisualizerFxAndUI() {

		// Creating a Visualizer on the output mix (audio session 0)
		// need permission MODIFY_AUDIO_SETTINGS
		mVisualizer = new Visualizer(0);

		mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
		mVisualizer.setDataCaptureListener(
				new Visualizer.OnDataCaptureListener() {
					public void onWaveFormDataCapture(Visualizer visualizer,
							byte[] bytes, int samplingRate) {
						visualizerView.updateVisualizer(bytes);
					}

					public void onFftDataCapture(Visualizer visualizer,
							byte[] bytes, int samplingRate) {
					}
				}, Visualizer.getMaxCaptureRate() / 2, true, false);

	}
}
