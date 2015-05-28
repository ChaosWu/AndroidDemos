package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.R;
import cn.android.demo.apis.ui.widget.CustomDialog.Builder;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 显示自定义Dialog
 * @author Elroy
 *
 */
public class DialogActivity extends Activity implements OnClickListener {
	Button buttonIOS;
	Button buttonAnd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_widget_show_dialog_activity);

		buttonAnd = (Button) findViewById(R.id.ui_widget_show_dialog_android);
		buttonIOS = (Button) findViewById(R.id.ui_widget_show_dialog_ios);

		buttonAnd.setOnClickListener(this);
		buttonIOS.setOnClickListener(this);
	}

	public void showIosDialog() {
		CustomDialog.Builder builder = new Builder(this);
		builder.setTitle(R.string.prompt);
		builder.setMessage(R.string.exit_app);
		builder.setPositiveButton(R.string.confirm, null);
		builder.setNegativeButton(R.string.cancel, null);
		builder.create().show();
	}

	public void showAndroidDialog() {
		AlertDialog.Builder mbuilder = new AlertDialog.Builder(this);
		mbuilder.setTitle(R.string.prompt);
		mbuilder.setMessage(R.string.exit_app);
		mbuilder.setPositiveButton(R.string.confirm, null);
		mbuilder.setNegativeButton(R.string.cancel, null);
		mbuilder.create().show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ui_widget_show_dialog_android:
			showAndroidDialog();
			break;
		case R.id.ui_widget_show_dialog_ios:
			showIosDialog();
			break;
		default:
			break;
		}
	}
}
