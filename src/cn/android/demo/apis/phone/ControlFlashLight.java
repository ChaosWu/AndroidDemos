package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 控制闪光灯
 * 
 * @author Elroy
 * 
 */
public class ControlFlashLight extends Activity {
	Camera camera = null;
	Parameters parameters;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_control_flash_light);
		final Button FlashLightControl = (Button) findViewById(R.id.flashcontrol);
		FlashLightControl.setText("Set FLASH_MODE_TORCH");

		FlashLightControl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (camera == null) {
					camera = Camera.open();
					parameters = camera.getParameters();
					parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
					camera.setParameters(parameters);
					FlashLightControl.setText("Set FLASH_MODE_OFF");
				} else {
					parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
					camera.setParameters(parameters);
					camera.release();
					camera = null;
					FlashLightControl.setText("Set FLASH_MODE_TORCH");
				}

			}
		});
	}
}