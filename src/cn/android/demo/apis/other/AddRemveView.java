package cn.android.demo.apis.other;

import cn.android.demo.apis.R;
import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class AddRemveView extends Activity {

	EditText textIn;
	Button buttonAdd;
	LinearLayout container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_add_remove_view);
		textIn = (EditText) findViewById(R.id.et_ohter_textin);
		buttonAdd = (Button) findViewById(R.id.bt_other_add_view);
		container = (LinearLayout) findViewById(R.id.ll_other_container);

		buttonAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				final View addView = layoutInflater.inflate(
						R.layout.add_remove_view, null);
				TextView textOut = (TextView) addView
						.findViewById(R.id.tv_add_remove);
				textOut.setText(textIn.getText().toString());
				Button buttonRemove = (Button) addView
						.findViewById(R.id.bt_add_remove);
				buttonRemove.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						((LinearLayout) addView.getParent())
								.removeView(addView);
					}
				});

				container.addView(addView);
			}
		});
		// 加上移动效果
		LayoutTransition layoutTransition = new LayoutTransition();
		container.setLayoutTransition(layoutTransition);

	}

}