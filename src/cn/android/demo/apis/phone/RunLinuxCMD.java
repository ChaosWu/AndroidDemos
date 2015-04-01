package cn.android.demo.apis.phone;

import java.io.IOException;
import java.io.InputStream;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 在大部分Android手机上能运行的Linux命令
 * 
 * @author Elroy
 * 
 */
public class RunLinuxCMD extends Activity {
	EditText cmdBox;
	Button btnRun;
	TextView textResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_run_linux_cmd);
		cmdBox = (EditText) findViewById(R.id.et_cmdbox);
		btnRun = (Button) findViewById(R.id.bt_run_linux);
		textResult = (TextView) findViewById(R.id.tv_linux_result);
		// 默认的等宽字体的正文样式
		textResult.setTypeface(Typeface.MONOSPACE);

		btnRun.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String[] cmd = cmdBox.getText().toString().split("\\s+");

				String cmdResult;
				try {
					cmdResult = runLinuxCmd(cmd);
					textResult.setTextColor(Color.WHITE);
					textResult.setText(cmdResult);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	protected String runLinuxCmd(String[] cmd) throws IOException {
		StringBuilder builder = new StringBuilder();
		ProcessBuilder processBuilder = new ProcessBuilder(cmd);
		Process process = processBuilder.start();

		InputStream is = process.getInputStream();

		int num;
		while ((num = is.read()) != -1) {

			builder.append((char) num);

		}

		return builder.toString();
	}
}
