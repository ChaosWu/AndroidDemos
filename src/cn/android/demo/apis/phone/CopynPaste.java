package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 复制粘贴功能
 * 
 * @author Elroy
 * 
 */
public class CopynPaste extends Activity {
	private Button btCopyPaste;
	private EditText etCopyPaste;
	private TextView tvCopyPaste;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.phone_copy_paste);
		btCopyPaste = (Button) findViewById(R.id.bt_copynpaste);
		etCopyPaste = (EditText) findViewById(R.id.et_copy_paste);
		tvCopyPaste = (TextView) findViewById(R.id.tv_copy_results);
		//
		final ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		btCopyPaste.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clipboardManager.setText(etCopyPaste.getText());

				tvCopyPaste.setText(clipboardManager.getText());

			}
		});
	}
}
