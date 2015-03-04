package cn.android.demo.apis.phone;

import java.util.ArrayList;
import java.util.List;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AndroidCameraPictureSizes extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_camera_picture_size);
		Spinner spinner = (Spinner) findViewById(R.id.supportedpicturesizes);

		Camera camera = Camera.open();
		Camera.Parameters parameters = camera.getParameters();

		List<Camera.Size> listSupportedPictureSizes = parameters
				.getSupportedPictureSizes();

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < listSupportedPictureSizes.size(); i++) {
			String strSize = String.valueOf(i) + " : "
					+ String.valueOf(listSupportedPictureSizes.get(i).height)
					+ " x "
					+ String.valueOf(listSupportedPictureSizes.get(i).width);

			list.add(strSize);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_item, list);
		//
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		// 释放相机资源
		camera.release();

	}
}
