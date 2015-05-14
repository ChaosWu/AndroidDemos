package cn.android.demo.apis.java;

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
import android.widget.Toast;

/**
 * 在运行时获取 子视图
 * 
 * @author Elroy
 * 
 */
@SuppressLint("NewApi")
public class GetChildViewAtRuntime extends Activity {

	EditText textIn;
	Button btAdd;
	LinearLayout container;
	Button btShow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_get_child_view_at_runtime);

		textIn = $id(R.id.get_child_view_at_runtime_textin);
		btAdd = $id(R.id.get_child_view_at_runtime_add);
		container = $id(R.id.get_child_view_at_runtime_container);
		btShow = $id(R.id.get_child_view_at_runtime_showall);

		btAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				final View addView = layoutInflater.inflate(
						R.layout.java_get_child_view_at_runtime_row, null);
				final TextView textOut = (TextView) addView
						.findViewById(R.id.get_child_view_at_runtime_textout);
				textOut.setText(textIn.getText().toString());

				Button btRemove = (Button) addView
						.findViewById(R.id.get_child_view_at_runtime_remove);
				btRemove.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						((LinearLayout) addView.getParent())
								.removeView(addView);

					}
				});

				Button btInsert = (Button) addView
						.findViewById(R.id.get_child_view_at_runtime_insert);
				btInsert.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String text = textOut.getText().toString();
						String newText = textIn.getText().toString() + text;

						textIn.setText(newText);

					}

				});
				container.addView(addView, 0);
			}
		});
		// Layout的动画
		// LayoutTransition transition = new LayoutTransition();
		// container.setLayoutTransition(transition);

		btShow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String showPrompt = "";
				int childCount = container.getChildCount();
				showPrompt += "ChildCount:" + childCount + "\n\n";
				for (int i = 0; i < childCount; i++) {
					View childView = container.getChildAt(i);
					TextView childTv = (TextView) childView
							.findViewById(R.id.get_child_view_at_runtime_textout);
					String childTvText = (String) childTv.getText();

					showPrompt += i + " :" + childTvText + "\n";

				}
				Toast.makeText(GetChildViewAtRuntime.this, showPrompt,
						Toast.LENGTH_LONG).show();
			}
		});

	}

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}
}
