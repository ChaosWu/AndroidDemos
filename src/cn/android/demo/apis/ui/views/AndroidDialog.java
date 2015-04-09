package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AndroidDialog extends Activity {
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_dialog);
		button = (Button) findViewById(R.id.bt_show_android_dialog);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openDialog();
			}
		});
	}

	protected void openDialog() {
		final Dialog dialog = new Dialog(this);
		dialog.setTitle("Animation Dialog");
		dialog.setContentView(R.layout.dialog_layout);
		dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

		Button dismiss = (Button) dialog.getWindow().findViewById(
				R.id.bt_dialog_dismiss);
		dismiss.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();

	}
}
