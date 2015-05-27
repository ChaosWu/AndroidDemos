package cn.android.demo.apis.ui.resources;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LoadResourcesFromXML extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TypedArray arrayResource = getResources().obtainTypedArray(
				R.array.resicion);
		TypedArray arrayColors = getResources().obtainTypedArray(
				R.array.rescolor);

		LinearLayout layout = new LinearLayout(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(params);

		for (int i = 0; i < arrayResource.length(); i++) {
			ImageView imageView = new ImageView(this);
			imageView.setImageDrawable(arrayResource.getDrawable(i));
			imageView.setBackgroundColor(arrayColors.getColor(i, 0xFF000000));
			layout.addView(imageView);

		}

		setContentView(layout);
		// 还给一个以前提取的阵列，供以后再使用。

		// 主要为了缓存，recycle被调用后，说明这个对象可以
		// 被重用，TypedArray内部持有部分数组，他们缓存
		// 在Resources类中的静态字段中，这样下次就不需要分配内存
		arrayResource.recycle();
		arrayColors.recycle();
	}
}
