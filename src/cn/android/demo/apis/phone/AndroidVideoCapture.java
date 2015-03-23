package cn.android.demo.apis.phone;

import java.io.IOException;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.media.MediaRecorder.AudioSource;
import android.media.MediaRecorder.VideoSource;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 视频采集
 * 
 * @see MediaRecorder
 * 
 * @author Elroy
 * 
 */
public class AndroidVideoCapture extends Activity implements
		SurfaceHolder.Callback {
	private Button bt;
	private MediaRecorder mediaRecorder;
	private SurfaceHolder surfaceHolder;
	private SurfaceView surfaceView;
	// TODO 
	private CamcorderProfile camcorderProfile;

	private boolean recording = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initMediaRecorder();
		setContentView(R.layout.phone_android_video_capture);

		surfaceView = (SurfaceView) findViewById(R.id.sv_video_view);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);

		/* SURFACE_TYPE_NORMAL：用RAM缓存原生数据的普通Surface */

		/* SURFACE_TYPE_HARDWARE：适用于DMA(Direct memory access )引擎和硬件加速的Surface */

		/* SURFACE_TYPE_GPU：适用于GPU加速的Surface */

		/*
		 * SURFACE_TYPE_PUSH_BUFFERS：表明该Surface不包含原生数据，
		 * Surface用到的数据由其他对象提供，在Camera图像预览中就
		 * 使用该类型的Surface，有Camera负责提供给预览Surface数据， 这样图像预览会比较流畅。如果设置这种类型则就不能调
		 * 用lockCanvas来获取Canvas对象了。需要注意的是，在高版 本的Android
		 * SDK中，setType这个方法已经被depreciated了。
		 */

		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		bt = (Button) findViewById(R.id.bt_video_capture_start);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (recording) {
					mediaRecorder.stop();
					mediaRecorder.release();
					// finish();
				} else {
					recording = true;
					mediaRecorder.start();
					bt.setText("STOP");
				}
			}
		});
	}

	private void initMediaRecorder() {
		mediaRecorder = new MediaRecorder();
		camcorderProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);

		mediaRecorder.setAudioSource(AudioSource.DEFAULT);
		mediaRecorder.setVideoSource(VideoSource.DEFAULT);
		mediaRecorder.setProfile(camcorderProfile);
		mediaRecorder.setOutputFile(Environment.getExternalStorageDirectory()
				+ "/" + System.currentTimeMillis() + ".mp4");

		mediaRecorder.setMaxDuration(60000);
		mediaRecorder.setMaxFileSize(500000);
	}

	private void prepareMediaRecorder() {
		mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());

		try {
			mediaRecorder.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// >>>>> Surface 回调方法
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		prepareMediaRecorder();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}
	// <<<<< Surface 回调方法
}
