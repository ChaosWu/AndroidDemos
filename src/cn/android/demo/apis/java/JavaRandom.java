package cn.android.demo.apis.java;

import java.util.Random;

import cn.android.demo.apis.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class JavaRandom extends Activity {

	SeekBar seekbarRange;
	TextView textOut;
	Button buttonGen;


	Random random;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_random);

		seekbarRange = (SeekBar) findViewById(R.id.random_seekbarrange);
		textOut = (TextView) findViewById(R.id.random_printout);
		buttonGen = (Button) findViewById(R.id.random_buttongen);

		buttonGen.setOnClickListener(onClickListener);

		random = new Random();
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {

			int range = seekbarRange.getProgress();
			int randomNum;

			if (range == 0) {
				randomNum = random.nextInt();
			} else {
				randomNum = random.nextInt(range + 1);
			}

			String result = "default: " + Integer.toString(randomNum) + "\n"
					+ "Binary: " + Integer.toBinaryString(randomNum) + "\n"
					+ "Binary: " + Integer.toOctalString(randomNum) + "\n"
					+ "Radix 10: " + Integer.toString(randomNum, 10) + "\n"
					+ "Hex: " + Integer.toHexString(randomNum) + "\n"
					+ "Radix 32: " + Integer.toString(randomNum, 32);

			textOut.setText(result);

		}
	};

}