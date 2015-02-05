package cn.android.demo.apis.phone;

import java.io.IOException;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AndroidCamera extends Activity implements OnClickListener,
		SurfaceHolder.Callback {
	private Button start;
	private Button stop;
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;

	private Camera camera;

	boolean previewing = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.phone_android_camera);

		start = (Button) findViewById(R.id.bt_start_camera_preview);
		stop = (Button) findViewById(R.id.bt_stop_camera_preview);

		getWindow().setFormat(PixelFormat.UNKNOWN);
		surfaceView = (SurfaceView) findViewById(R.id.sv_camera);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		start.setOnClickListener(this);
		stop.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_start_camera_preview:
			ToastUtil.showToast(this, "start !");
			if (!previewing) {
				camera = Camera.open();

				if (camera != null) {
					try {
						camera.setPreviewDisplay(surfaceHolder);
						camera.startPreview();
						previewing = true;
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			}

			break;
		case R.id.bt_stop_camera_preview:
			ToastUtil.showToast(this, "stop !");
			if (camera != null && previewing) {
				camera.stopPreview();
				camera.release();
				camera = null;
				previewing = false;
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		camera = Camera.open();
	}
	
	

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

		if (previewing) {
			camera.stopPreview();
			previewing = false;
		}

		if (camera != null) {
			try {
				camera.setPreviewDisplay(surfaceHolder);
				camera.startPreview();
				previewing = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopPreview();
		camera.release();
		camera = null;
		previewing = false;
	}
}
