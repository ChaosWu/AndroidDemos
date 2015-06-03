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


//
//public class MainActivity extends ActionBarActivity {
//	 
//	 EditText cmdBox;
//	 Button btnRun;
//	 TextView textResult;
//
//	    @Override
//	    protected void onCreate(Bundle savedInstanceState) {
//	        super.onCreate(savedInstanceState);
//	        setContentView(R.layout.activity_main);
//	        
//	        cmdBox = (EditText)findViewById(R.id.cmdbox);
//	     btnRun = (Button)findViewById(R.id.run);
//	     textResult = (TextView)findViewById(R.id.result);
//	     textResult.setTypeface(Typeface.MONOSPACE);
//	        
//	  btnRun.setOnClickListener(new OnClickListener(){
//
//	   @Override
//	   public void onClick(View v) {
//	    //Split String from EditText to String Array
//	    String[] cmd = cmdBox.getText().toString().split("\\s+"); 
//	    try {
//	     String cmdResult = runLinuxCmd(cmd);
//	     textResult.setTextColor(Color.WHITE);
//	     textResult.setText(cmdResult);
//	    } catch (IOException e) {
//	     e.printStackTrace();
//	     textResult.setTextColor(Color.RED);
//	     textResult.setText("Something Wrong!\n"
//	      + e.getMessage());
//	    }
//	   }});
//	    }
//
//	    //Run a Linux command and return result
//	 private String runLinuxCmd(String[] command) throws IOException {
//
//	    StringBuilder cmdReturn = new StringBuilder();
//
//	    ProcessBuilder processBuilder = new ProcessBuilder(command);
//	     Process process = processBuilder.start();
//
//	     InputStream inputStream = process.getInputStream();
//	     int c;
//
//	     while ((c = inputStream.read()) != -1) {
//	      cmdReturn.append((char) c);
//	     }
//
//	     return cmdReturn.toString();
//	   }
//	}
