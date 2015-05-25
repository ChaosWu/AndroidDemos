package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.ui.widget.TouchToToggleViewV.OnToggledListener;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class TouchToToggleViewActivity extends Activity implements OnToggledListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TouchToToggleViewV view = new TouchToToggleViewV(this);
		setContentView(view);
		view.setOnToggledListener(this);
	}

	@Override
	public void OnToggled(TouchToToggleViewV v, boolean touchOn) {
		// get the id string
		// String idString = getResources().getResourceName(v.getId());

		Toast.makeText(TouchToToggleViewActivity.this,
				"Toogled:\n" + "idString" + "\n" + touchOn, Toast.LENGTH_SHORT)
				.show();
	}
}
