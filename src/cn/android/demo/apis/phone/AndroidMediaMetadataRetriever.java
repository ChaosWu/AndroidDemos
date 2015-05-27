package cn.android.demo.apis.phone;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.android.demo.apis.R;
import cn.android.demo.utils.AnimatedGifEncoder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 从API级别10加入，提供一个统一的接口，用于从输入媒体文件中检索帧和元数据。
 * 
 * 这个例子说明如何交互检索的MP4文件框架
 * 
 * setContentView(R.layout.phone_android_media_metadata_retriever);
 * 
 * btnOpen = (Button) findViewById(R.id.media_metadata_retriever_open);
 * 
 * @author Elroy
 * 
 */
@SuppressLint("NewApi")
public class AndroidMediaMetadataRetriever extends Activity {
	ImageView capturedImageView;
	Button btnSave;
	Button btnOpen;
	TextView textInfo, textMaxDur, textCurDur;
	SeekBar timeFrameBar;

	long maxDur;

	MediaMetadataRetriever mediaMetadataRetriever = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_media_metadata_retriever);

		btnOpen = (Button) findViewById(R.id.media_metadata_retriever_open);
		btnSave = (Button) findViewById(R.id.media_metadata_retriever_save);
		textInfo = (TextView) findViewById(R.id.media_metadata_retriever_info);
		textMaxDur = (TextView) findViewById(R.id.media_metadata_retriever_maxdur);
		textCurDur = (TextView) findViewById(R.id.media_metadata_retriever_curdur);
		timeFrameBar = (SeekBar) findViewById(R.id.media_metadata_retriever_timeframe);

		capturedImageView = (ImageView) findViewById(R.id.media_metadata_retriever_capturedimage);

		mediaMetadataRetriever = new MediaMetadataRetriever();

		btnOpen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Intent.ACTION_GET_CONTENT,
						Images.Media.EXTERNAL_CONTENT_URI);
				intent.setType("video/mp4");
				startActivityForResult(intent, 0);
			}
		});

		btnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mediaMetadataRetriever != null) {
					TaskSaveGIF myTaskSaveGIF = new TaskSaveGIF(timeFrameBar);
					myTaskSaveGIF.execute();
				}
			}
		});

		timeFrameBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				updateFrame();
			}
		});
	}

	private void updateFrame() {
		int frameProgress = timeFrameBar.getProgress();

		long frameTime = maxDur * frameProgress / 100;

		textCurDur.setText(String.valueOf(frameTime) + " us");
		Bitmap bmFrame = mediaMetadataRetriever.getFrameAtTime(frameTime);
		capturedImageView.setImageBitmap(bmFrame);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			Uri uri = data.getData();
			textInfo.setText(uri.toString());

			MediaMetadataRetriever tRetriever = new MediaMetadataRetriever();

			try {
				tRetriever.setDataSource(getBaseContext(), uri);

				mediaMetadataRetriever = tRetriever;
				// extract duration in millisecond, as String
				String DURATION = mediaMetadataRetriever
						.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
				textMaxDur.setText(DURATION + " ms");
				// convert to us, as int
				maxDur = (long) (1000 * Double.parseDouble(DURATION));

				timeFrameBar.setProgress(0);
				updateFrame();
			} catch (RuntimeException e) {
				e.printStackTrace();
				Toast.makeText(AndroidMediaMetadataRetriever.this,
						"Something Wrong!", Toast.LENGTH_LONG).show();
			}

		}
	}

	public class TaskSaveGIF extends AsyncTask<Void, Integer, String> {

		SeekBar bar;

		public TaskSaveGIF(SeekBar sb) {
			bar = sb;
			Toast.makeText(AndroidMediaMetadataRetriever.this,
					"Generate GIF animation", Toast.LENGTH_LONG).show();
		}

		@Override
		protected String doInBackground(Void... params) {
			String extStorageDirectory = Environment
					.getExternalStorageDirectory().toString();
			File outFile = new File(extStorageDirectory, "test.GIF");
			try {
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(outFile));
				bos.write(genGIF());
				bos.flush();
				bos.close();

				return (outFile.getAbsolutePath() + " Saved");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return e.getMessage();
			}
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(AndroidMediaMetadataRetriever.this, result,
					Toast.LENGTH_LONG).show();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			bar.setProgress(values[0]);
			updateFrame();
		}

		private byte[] genGIF() {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			AnimatedGifEncoder animatedGifEncoder = new AnimatedGifEncoder();
			animatedGifEncoder.setDelay(1000);

			Bitmap bmFrame;
			animatedGifEncoder.start(bos);
			for (int i = 0; i < 100; i += 10) {
				long frameTime = maxDur * i / 100;
				bmFrame = mediaMetadataRetriever.getFrameAtTime(frameTime);
				animatedGifEncoder.addFrame(bmFrame);
				publishProgress(i);
			}

			// last from at end
			bmFrame = mediaMetadataRetriever.getFrameAtTime(maxDur);
			animatedGifEncoder.addFrame(bmFrame);
			publishProgress(100);

			animatedGifEncoder.finish();
			return bos.toByteArray();
		}
	}

}