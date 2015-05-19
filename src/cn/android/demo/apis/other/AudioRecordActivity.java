package cn.android.demo.apis.other;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * 语音转换
 * 
 * 声音录制，变频，变调
 * 
 * @author Elroy
 * 
 */
public class AudioRecordActivity extends Activity {
	String[] freqText = { "11.025 KHz(Lowest)", "16.000KHz", "22.050KHz",
			"44.100 KHz (Highest)" };

	Integer[] freqSet = { 11025, 16000, 22050, 44100 };

	private ArrayAdapter<String> adapter;

	Spinner spinner;
	Button startRec;
	Button stopRec;
	Button playBack;

	Boolean recording;

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_audio_record);

		startRec = $id(R.id.audio_record_startrec);
		stopRec = $id(R.id.audio_record_stoprec);
		playBack = $id(R.id.audio_record_playback);

		startRec.setOnClickListener(startRecOnClickListener);
		stopRec.setOnClickListener(stopRecOnClickListener);
		playBack.setOnClickListener(playBackOnClickListener);

		spinner = $id(R.id.audio_record_frequency);
		adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_item, freqText);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		stopRec.setEnabled(false);

	}

	OnClickListener startRecOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {

			Thread recordThread = new Thread(new Runnable() {

				@Override
				public void run() {
					recording = true;
					startRecord();
				}

			});

			recordThread.start();
			startRec.setEnabled(false);
			stopRec.setEnabled(true);

		}
	};

	OnClickListener stopRecOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			recording = false;
			startRec.setEnabled(true);
			stopRec.setEnabled(false);
		}
	};

	OnClickListener playBackOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			playRecord();
		}

	};

	private void startRecord() {

		File file = new File(Environment.getExternalStorageDirectory(),
				"test.pcm");

		int selectedPos = spinner.getSelectedItemPosition();
		int sampleFreq = freqSet[selectedPos];

		final String promptStartRecord = "startRecord()\n"
				+ file.getAbsolutePath() + "\n"
				+ (String) spinner.getSelectedItem();

		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(AudioRecordActivity.this, promptStartRecord,
						Toast.LENGTH_LONG).show();
			}
		});

		try {
			file.createNewFile();

			OutputStream outputStream = new FileOutputStream(file);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					outputStream);
			DataOutputStream dataOutputStream = new DataOutputStream(
					bufferedOutputStream);

			int minBufferSize = AudioRecord.getMinBufferSize(sampleFreq,
					AudioFormat.CHANNEL_CONFIGURATION_MONO,
					AudioFormat.ENCODING_PCM_16BIT);

			short[] audioData = new short[minBufferSize];

			AudioRecord audioRecord = new AudioRecord(
					MediaRecorder.AudioSource.MIC, sampleFreq,
					AudioFormat.CHANNEL_CONFIGURATION_MONO,
					AudioFormat.ENCODING_PCM_16BIT, minBufferSize);

			audioRecord.startRecording();

			while (recording) {
				int numberOfShort = audioRecord.read(audioData, 0,
						minBufferSize);
				for (int i = 0; i < numberOfShort; i++) {
					dataOutputStream.writeShort(audioData[i]);
				}
			}

			audioRecord.stop();
			dataOutputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void playRecord() {

		File file = new File(Environment.getExternalStorageDirectory(),
				"test.pcm");

		int shortSizeInBytes = Short.SIZE / Byte.SIZE;

		int bufferSizeInBytes = (int) (file.length() / shortSizeInBytes);
		short[] audioData = new short[bufferSizeInBytes];

		try {
			InputStream inputStream = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					inputStream);
			DataInputStream dataInputStream = new DataInputStream(
					bufferedInputStream);

			int i = 0;
			while (dataInputStream.available() > 0) {
				audioData[i] = dataInputStream.readShort();
				i++;
			}

			dataInputStream.close();

			int selectedPos = spinner.getSelectedItemPosition();
			int sampleFreq = freqSet[selectedPos];

			final String promptPlayRecord = "PlayRecord()\n"
					+ file.getAbsolutePath() + "\n"
					+ (String) spinner.getSelectedItem();

			Toast.makeText(AudioRecordActivity.this, promptPlayRecord,
					Toast.LENGTH_LONG).show();

			AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
					sampleFreq, AudioFormat.CHANNEL_CONFIGURATION_MONO,
					AudioFormat.ENCODING_PCM_16BIT, bufferSizeInBytes,
					AudioTrack.MODE_STREAM);

			audioTrack.play();
			audioTrack.write(audioData, 0, bufferSizeInBytes);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}