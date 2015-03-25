package cn.android.demo.apis.java;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JavaAddView extends Activity {
	LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_code_add_view);
		layout = (LinearLayout) findViewById(R.id.ll_java_code_add_view);
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(R.drawable.chaoswu);

		TextView textView = new TextView(this);
		textView.setText("这是java代码添加的阴影");

		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		imageView.setLayoutParams(layoutParams);
		textView.setShadowLayer(5f, 10f, 10f, 0xFFffffff);

		layout.addView(imageView);
		layout.addView(textView);
	}
}
