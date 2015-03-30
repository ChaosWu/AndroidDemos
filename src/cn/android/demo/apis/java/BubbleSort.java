package cn.android.demo.apis.java;

import java.util.Random;

import cn.android.demo.apis.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BubbleSort extends Activity {
	int[] data = new int[10];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		setContentView(textView);

		Random random = new Random();
		String src = "Src= ";
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(100);
			src += data[i] + " , ";
		}
		textView.setText(src);
		bubblesort();

		String result = "Result= ";
		for (int i = 0; i < data.length; i++) {
			result += data[i] + " , ";
		}
		textView.append("\n" + result);
		// TODO 支持标签语言
		textView.append("\n" + getResources().getString(R.string.stylingtext));

	}

	/** 冒泡排序 */
	private void bubblesort() {
		for (int i = 0; i < data.length - 1; i++) {
			for (int j = 0; j < data.length - i - 1; j++) {
				if (data[j] > data[j + 1]) {
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
		}

	}
}
