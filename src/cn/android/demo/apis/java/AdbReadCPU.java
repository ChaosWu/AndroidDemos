package cn.android.demo.apis.java;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import cn.android.demo.apis.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 通过Adb读取 cpu信息
 * 
 * adb shell ls /sys/devices/system/cpu/
 * 
 * @author Elroy
 * 
 */
public class AdbReadCPU extends Activity {
	TextView textAvProc, textNumOfCpu, textMsg;
	Button btnReadCpuFreq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_adb_read_cpu);
		textAvProc = (TextView) findViewById(R.id.adb_read_cpu_avproc);
		textNumOfCpu = (TextView) findViewById(R.id.adb_read_cpu_numofcpu);
		textMsg = (TextView) findViewById(R.id.adb_read_cpu_msg);
		btnReadCpuFreq = (Button) findViewById(R.id.adb_read_cpu_readfreq);

		Runtime runtime = Runtime.getRuntime();
		int availableProcessors = runtime.availableProcessors();
		textAvProc.setText("availableProcessors = " + availableProcessors);

		btnReadCpuFreq.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				readCpuFreqNow();
			}
		});

		readCpuFreqNow();
	}

	private void readCpuFreqNow() {
		File[] cpuFiles = getCPUs();
		textNumOfCpu.setText("number of cpu: " + cpuFiles.length);

		String strFileList = "";
		for (int i = 0; i < cpuFiles.length; i++) {

			String path_scaling_cur_freq = cpuFiles[i].getAbsolutePath()
					+ "/cpufreq/scaling_cur_freq";

			String scaling_cur_freq = cmdCat(path_scaling_cur_freq);

			strFileList += i + ": " + path_scaling_cur_freq + "\n"
					+ scaling_cur_freq + "\n";
		}

		textMsg.setText(strFileList);
	}

	// run Linux command
	// $ cat f
	private String cmdCat(String f) {

		String[] command = { "cat", f };
		StringBuilder cmdReturn = new StringBuilder();

		try {
			ProcessBuilder processBuilder = new ProcessBuilder(command);
			Process process = processBuilder.start();

			InputStream inputStream = process.getInputStream();
			int c;

			while ((c = inputStream.read()) != -1) {
				cmdReturn.append((char) c);
			}

			return cmdReturn.toString();

		} catch (IOException e) {
			e.printStackTrace();
			return "Something Wrong";
		}

	}

	/*
	 * Get file list of the pattern /sys/devices/system/cpu/cpu[0..9]
	 */
	//adb shell cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq
	private File[] getCPUs() {

		class CpuFilter implements FileFilter {
			@Override
			public boolean accept(File pathname) {
				if (Pattern.matches("cpu[0-9]+", pathname.getName())) {
					return true;
				}
				return false;
			}
		}

		File dir = new File("/sys/devices/system/cpu/");
		File[] files = dir.listFiles(new CpuFilter());
		return files;
	}
}
