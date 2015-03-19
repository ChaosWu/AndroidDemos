package cn.android.demo.apis.ui.graphics;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * TODO 未完成 => 模糊化效果
 * 
 * @author Elroy
 * 
 */
public class AndroidBlur extends Activity {

	private SeekBar bar;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_graphics_blur);
		bar = (SeekBar) findViewById(R.id.sb_blur);
	}
}
