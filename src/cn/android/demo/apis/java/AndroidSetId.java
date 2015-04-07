package cn.android.demo.apis.java;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * java代码添加 id
 * 
 * @see values ids.xml
 * @author Elroy
 * 
 */
public class AndroidSetId extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setId(R.id.layout_id);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		linearLayout.setLayoutParams(layoutParams);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		ImageView imageView = new ImageView(this);
		imageView.setId(R.id.image_id);
		imageView.setImageResource(R.drawable.chaoswu);

		LayoutParams layoutParams2 = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		imageView.setLayoutParams(layoutParams2);

		linearLayout.addView(imageView);

		setContentView(linearLayout);

		linearLayout.setOnClickListener(clickListener);
		imageView.setOnClickListener(clickListener);

	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			ToastUtil.showToast(getBaseContext(), "ID：" + v.getId()
					+ "  >clicked!");
		}
	};
}
