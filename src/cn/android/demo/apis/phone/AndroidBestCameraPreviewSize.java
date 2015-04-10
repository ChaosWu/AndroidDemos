package cn.android.demo.apis.phone;

import java.io.IOException;
import java.util.List;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * 确定最佳的摄像头预览大小 It'a例子来确定最佳的支持相机预览大小，有最大的预览区域。
 * 
 * @author Elroy
 * 
 */
public class AndroidBestCameraPreviewSize extends Activity {

	Camera myCamera;
	SurfaceView mySurfaceView;
	SurfaceHolder mySurfaceHolder;
	boolean isPreview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_bast_camera_preview_size);

		isPreview = false;
		mySurfaceView = (SurfaceView) findViewById(R.id.sv_beat_camera_preview_size_bmypreview);
		mySurfaceHolder = mySurfaceView.getHolder();
		mySurfaceHolder.addCallback(mySurfaceCallback);

	}

	SurfaceHolder.Callback mySurfaceCallback = new SurfaceHolder.Callback() {

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			Camera.Parameters myParameters = myCamera.getParameters();
			Camera.Size myBestSize = getBestPreviewSize(width, height,
					myParameters);

			if (myBestSize != null) {
				myParameters
						.setPreviewSize(myBestSize.width, myBestSize.height);
				myCamera.setParameters(myParameters);
				myCamera.startPreview();
				isPreview = true;

				Toast.makeText(
						getApplicationContext(),
						"Best Size:\n" + String.valueOf(myBestSize.width)
								+ " : " + String.valueOf(myBestSize.height),
						Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			try {
				myCamera.setPreviewDisplay(mySurfaceHolder);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub

		}

	};

	private Camera.Size getBestPreviewSize(int width, int height,
			Camera.Parameters parameters) {
		Camera.Size bestSize = null;
		List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();

		bestSize = sizeList.get(0);

		for (int i = 1; i < sizeList.size(); i++) {
			if ((sizeList.get(i).width * sizeList.get(i).height) > (bestSize.width * bestSize.height)) {
				bestSize = sizeList.get(i);
			}
		}

		return bestSize;
	}

	@Override
	protected void onResume() {
		super.onResume();
		myCamera = Camera.open();
	}

	@Override
	protected void onPause() {

		if (isPreview) {
			myCamera.stopPreview();
		}

		myCamera.release();
		myCamera = null;
		isPreview = false;

		super.onPause();
	}

}