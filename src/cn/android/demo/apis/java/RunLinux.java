package cn.android.demo.apis.java;

import java.io.IOException;
import java.io.InputStream;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RunLinux extends Activity {
	TextView textView;
	TextView tvcommand;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.java_run_linux);
		textView = (TextView) findViewById(R.id.run_linux_prompt);
		tvcommand = (TextView) findViewById(R.id.run_linux_textcommand);

		// String[] command = { "ls", "-s" };
		StringBuilder sb = new StringBuilder();
		String[] command = { "cat",
				"/system/etc/permissions/handheld_core_hardware.xml" };
		String stringCommand = "$ ";
		for (int i = 0; i < command.length; i++) {
			stringCommand += command[i] + " ";
		}
		tvcommand.setText(stringCommand + "\n\n");
		try {
			ProcessBuilder pb = new ProcessBuilder(command);
			Process process = pb.start();

			InputStream is = process.getInputStream();
			int c;
			while ((c = is.read()) != -1) {
				sb.append((char) c);
			}

			textView.setText(sb.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
