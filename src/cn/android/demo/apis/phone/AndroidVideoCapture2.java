package cn.android.demo.apis.phone;

import java.io.IOException;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class AndroidVideoCapture2 extends Activity {
	private Camera myCamera;
	private MediaRecorder mediaRecorder;
	private MyCameraSurfaceView myCameraSurfaceView;

	Button myButton;
	SurfaceHolder surfaceHolder;
	boolean recording;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		recording = false;
		setContentView(R.layout.phone_android_video_capture2);
		myCamera = getCameraInstance();
		if (myCamera == null) {
			ToastUtil.showToast(this, "Fail to get Camera");
		}

		myCameraSurfaceView = new MyCameraSurfaceView(this, myCamera);
		FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_video_capture2);
		frameLayout.addView(myCameraSurfaceView);

		myButton = (Button) findViewById(R.id.bt_video_capture2);
		myButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (recording) {
					mediaRecorder.stop();
					releaseMediaRecorder();
					finish();
				} else {
					// MediaRecorder开始前发布相机
					releaseCamera();
					if (!prepareMediaRecorder()) {
						ToastUtil.showToast(AndroidVideoCapture2.this,
								"Fail in prepareMediaRecorder()!\n - Ended -");
						finish();
					}
					mediaRecorder.start();
					recording = true;
					myButton.setText("stop");
				}
			}
		});

	}

	@Override
	protected void onPause() {
		super.onPause();
		releaseMediaRecorder(); // if you are using MediaRecorder, release it
								// first
		releaseCamera(); // release the camera immediately on pause event
	}

	protected boolean prepareMediaRecorder() {
		myCamera = getCameraInstance();

		// 设置闪光灯
		Parameters parameters = myCamera.getParameters();
		
		String open = Parameters.FLASH_MODE_TORCH;
		String close = Parameters.FLASH_MODE_OFF;
		
		parameters.setFlashMode(open);
		myCamera.setParameters(parameters);

		mediaRecorder = new MediaRecorder();

		myCamera.unlock();
		mediaRecorder.setCamera(myCamera);

		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
		mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

		mediaRecorder.setProfile(CamcorderProfile
				.get(CamcorderProfile.QUALITY_HIGH));

		mediaRecorder.setOutputFile("/sdcard/myvideo.mp4");
		mediaRecorder.setMaxDuration(60000); // Set max duration 60 sec.
		mediaRecorder.setMaxFileSize(5000000); // Set max file size 5M

		mediaRecorder.setPreviewDisplay(myCameraSurfaceView.getHolder()
				.getSurface());

		try {
			mediaRecorder.prepare();
		} catch (IllegalStateException e) {
			releaseMediaRecorder();
			return false;
		} catch (IOException e) {
			releaseMediaRecorder();
			return false;
		}
		return true;

	}

	protected void releaseCamera() {
		if (myCamera != null) {
			myCamera.release();
			myCamera = null;

		}
	}

	protected void releaseMediaRecorder() {
		if (mediaRecorder != null) {
			mediaRecorder.reset();// 清晰的记录配置
			mediaRecorder.release();// 释放记录对象
			mediaRecorder = null;
			myCamera.lock();// 锁定摄像头，供以后使用
		}
	}

	private Camera getCameraInstance() {
		Camera c = null;
		c = Camera.open();
		return c;
	}

	public class MyCameraSurfaceView extends SurfaceView implements
			SurfaceHolder.Callback {
		private SurfaceHolder mHolder;
		private Camera mCamera;

		public MyCameraSurfaceView(Context context, Camera camera) {
			super(context);
			mCamera = camera;

			mHolder = getHolder();
			mHolder.addCallback(this);
			mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		}

		public MyCameraSurfaceView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			try {
				mCamera.setPreviewDisplay(holder);
				mCamera.startPreview();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			if (mHolder.getSurface() == null) {
				return;
			}
			mCamera.stopPreview();

			try {
				mCamera.setPreviewDisplay(mHolder);
				mCamera.startPreview();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub

		}

	}
}
