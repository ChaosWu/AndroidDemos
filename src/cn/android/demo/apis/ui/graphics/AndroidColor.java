package cn.android.demo.apis.ui.graphics;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

/**
 * 颜色表示由四部分组成
 * 
 * int rgb = (alpha << 24) | (red << 16) | (green << 8) | blue;
 * 
 * 
 * int alpha = (rgb & 0xFF000000) >>> 24;
 * 
 * int red = (rgb & 0x00FF0000) >>> 16;
 * 
 * int green = (rgb & 0x0000FF00) >>> 8;
 * 
 * int blue = (rgb & 0x000000FF) >>> 0;
 * 
 * @author Elroy
 * 
 */
public class AndroidColor extends Activity {
	private LinearLayout background;
	private Spinner spinner;

	private static final String[] color = { "BLACK", "BLUE", "CYAN", "DKGRAY",
			"GRAY", "GREEN", "LTGRAY", "MAGENTA", "RED", "TRANSPARENT",
			"WHITE", "YELLOW" };

	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_graphics_color);
		spinner = (Spinner) findViewById(R.id.spinner_color);
		background = (LinearLayout) findViewById(R.id.ll_background_color);

		adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_item, color);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(adapter);
		spinner.setSelection(0);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				setBackgroundColor(spinner.getSelectedItem().toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	protected void setBackgroundColor(String strColor) {
		ToastUtil.showToast(AndroidColor.this, strColor);

		if (strColor == "BLACK") {
			background.setBackgroundColor(Color.BLACK);
		} else if (strColor == "BLUE") {
			background.setBackgroundColor(Color.BLUE);
		} else if (strColor == "CYAN") {
			background.setBackgroundColor(Color.CYAN);
		} else if (strColor == "DKGRAY") {
			background.setBackgroundColor(Color.DKGRAY);
		} else if (strColor == "GRAY") {
			background.setBackgroundColor(Color.GRAY);
		} else if (strColor == "GREEN") {
			background.setBackgroundColor(Color.GREEN);
		} else if (strColor == "LTGRAY") {
			background.setBackgroundColor(Color.LTGRAY);
		} else if (strColor == "MAGENTA") {
			background.setBackgroundColor(Color.MAGENTA);
		} else if (strColor == "RED") {
			background.setBackgroundColor(Color.RED);
		} else if (strColor == "TRANSPARENT") {
			background.setBackgroundColor(Color.TRANSPARENT);
		} else if (strColor == "WHITE") {
			background.setBackgroundColor(Color.WHITE);
		} else if (strColor == "YELLOW") {
			background.setBackgroundColor(Color.YELLOW);
		}
	}
}
