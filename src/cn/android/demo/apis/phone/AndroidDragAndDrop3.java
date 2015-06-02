package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 拖拽图片
 * 
 * @author Elroy
 * 
 */
public class AndroidDragAndDrop3 extends Activity {

	LinearLayout area1, area2;
	TextView prompt;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.phone_android_drag_drop3);

		area1 = (LinearLayout) findViewById(R.id.drag_drop_3_area1);
		area2 = (LinearLayout) findViewById(R.id.drag_drop_3_area2);

		prompt = (TextView) findViewById(R.id.drag_drop_3_prompt);
		prompt.setMovementMethod(new ScrollingMovementMethod());
		prompt.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {

				prompt.setText("");

				return true;
			}
		});

		TypedArray arrayResources = getResources().obtainTypedArray(
				R.array.resicon);

		for (int i = 0; i < arrayResources.length(); i++) {
			ImageView imageView = new ImageView(this);
			imageView.setImageDrawable(arrayResources.getDrawable(i));
			imageView.setOnLongClickListener(myOnLongClickListener);
			area1.addView(imageView);

		}
		arrayResources.recycle();
		area1.setOnDragListener(myOnDragListener);
		area2.setOnDragListener(myOnDragListener);
	}

	OnLongClickListener myOnLongClickListener = new OnLongClickListener() {

		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@Override
		public boolean onLongClick(View v) {
			ClipData data = ClipData.newPlainText("", "");
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
			v.startDrag(data, shadowBuilder, v, 0);
			// v.setVisibility(View.INVISIBLE);
			return true;
		}

	};
	@SuppressLint("NewApi")
	OnDragListener myOnDragListener = new OnDragListener() {

		@Override
		public boolean onDrag(View v, DragEvent event) {

			String area;
			if (v == area1) {
				area = "area1";
			} else if (v == area2) {
				area = "area2";
			} else {
				area = "unknown";
			}

			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				prompt.append("ACTION_DRAG_STARTED: " + area + "\n");
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				prompt.append("ACTION_DRAG_ENTERED: " + area + "\n");
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				prompt.append("ACTION_DRAG_EXITED: " + area + "\n");
				break;
			case DragEvent.ACTION_DROP:
				prompt.append("ACTION_DROP: " + area + "\n");
				View view = (View) event.getLocalState();
				LinearLayout oldParent = (LinearLayout) view.getParent();
				oldParent.removeView(view);
				LinearLayout newParent = (LinearLayout) v;
				newParent.addView(view);
				break;
			case DragEvent.ACTION_DRAG_ENDED:
				prompt.append("ACTION_DRAG_ENDED: " + area + "\n");
			default:
				break;
			}
			return true;
		}

	};
}