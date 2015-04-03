package cn.android.demo.apis.phone;

import cn.android.demo.apis.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.widget.Button;
import android.widget.TextView;

/**
 * 系统拖放框架
 * 
 * 拖放的过程：
 * 
 * *****开始 ：startDrag(ClipData data, DragShadowBuilder shadowBuilder,
 * ObjectmyLocalState, int flags)
 * 
 * *****持续：
 * 
 * *****放下：
 * 
 * *****结束：
 * 
 * @author Elroy
 * 
 */
@SuppressLint("NewApi")
public class AndroidDragAndDrop extends Activity {

	Button button1, button2, button3, button4, buttonTarget;
	Button dragSourceButton;
	TextView comments;

	String commentMsg;
	// 从OnDragListener接口中创建一个拖动事件监听器对象
	MyDragEventListener myDragEventListener = new MyDragEventListener();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_drag_drop);
		button1 = (Button) findViewById(R.id.drag_drop_button1);
		button2 = (Button) findViewById(R.id.drag_drop_button2);
		button3 = (Button) findViewById(R.id.drag_drop_button3);
		button4 = (Button) findViewById(R.id.drag_drop_button4);
		buttonTarget = (Button) findViewById(R.id.drag_drop_buttonTarget);
		comments = (TextView) findViewById(R.id.drag_drop_comments);

		// Create and set the tags for the Buttons
		final String BUTTON1_TAG = "button1";
		final String BUTTON2_TAG = "button2";
		final String BUTTON3_TAG = "button3";
		final String BUTTON4_TAG = "button4";
		button1.setTag(BUTTON1_TAG);
		button2.setTag(BUTTON2_TAG);
		button3.setTag(BUTTON3_TAG);
		button4.setTag(BUTTON4_TAG);

		button1.setOnLongClickListener(sourceButtonsLongClickListener);
		button2.setOnLongClickListener(sourceButtonsLongClickListener);
		button3.setOnLongClickListener(sourceButtonsLongClickListener);
		button4.setOnLongClickListener(sourceButtonsLongClickListener);

		// 把拖动事件监听器对象设置给一个View对象
		button1.setOnDragListener(myDragEventListener);
		button2.setOnDragListener(myDragEventListener);
		button3.setOnDragListener(myDragEventListener);
		button4.setOnDragListener(myDragEventListener);
		buttonTarget.setOnDragListener(myDragEventListener);
	}

	Button.OnLongClickListener sourceButtonsLongClickListener = new Button.OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {

			// 固定写法 拷贝数据
			ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
			String[] clipDescription = { ClipDescription.MIMETYPE_TEXT_PLAIN };
			ClipData dragData = new ClipData((CharSequence) v.getTag(),
					clipDescription, item);
			DragShadowBuilder myShadow = new MyDragShadowBuilder(v);
			// 应用程序会告诉系统开始拖拽的动作
			v.startDrag(dragData, myShadow, null, 0);

			commentMsg = v.getTag() + " : onLongClick.\n";
			comments.setText(commentMsg);

			return true;
		}
	};

	private static class MyDragShadowBuilder extends View.DragShadowBuilder {
		private static Drawable shadow;

		public MyDragShadowBuilder(View v) {
			super(v);
			shadow = new ColorDrawable(Color.LTGRAY);
		}

		// startDrag() 立即调用 执行
		@Override
		public void onProvideShadowMetrics(Point size, Point touch) {
			int width = getView().getWidth();
			int height = getView().getHeight();

			shadow.setBounds(0, 0, width, height);
			size.set(width, height);
			touch.set(width / 2, height / 2);
		}

		@Override
		public void onDrawShadow(Canvas canvas) {
			shadow.draw(canvas);
		}

	}

	protected class MyDragEventListener implements View.OnDragListener {
		// 这是当调度拖动事件到侦听器的系统调用的方法。
		@Override
		public boolean onDrag(View v, DragEvent event) {
			// 定义一个变量来存储动作类型为传入事件
			final int action = event.getAction();
			// 处理每个的期望事件
			switch (action) {
			case DragEvent.ACTION_DRAG_STARTED:
				// All involved view accept ACTION_DRAG_STARTED for
				// MIMETYPE_TEXT_PLAIN

				if (event.getClipDescription().hasMimeType(
						ClipDescription.MIMETYPE_TEXT_PLAIN)) {
					commentMsg += ((Button) v).getText()
							+ " : ACTION_DRAG_STARTED accepted.\n";
					comments.setText(commentMsg);
					return true; // Accept
				} else {
					commentMsg += ((Button) v).getText()
							+ " : ACTION_DRAG_STARTED rejected.\n";
					comments.setText(commentMsg);
					return false; // reject
				}
			case DragEvent.ACTION_DRAG_ENTERED:
				commentMsg += ((Button) v).getText()
						+ " : ACTION_DRAG_ENTERED.\n";
				comments.setText(commentMsg);
				return true;
			case DragEvent.ACTION_DRAG_LOCATION:
				commentMsg += ((Button) v).getText()
						+ " : ACTION_DRAG_LOCATION - " + event.getX() + " : "
						+ event.getY() + "\n";
				comments.setText(commentMsg);
				return true;
			case DragEvent.ACTION_DRAG_EXITED:
				commentMsg += ((Button) v).getText()
						+ " : ACTION_DRAG_EXITED.\n";
				comments.setText(commentMsg);
				return true;
			case DragEvent.ACTION_DROP:
				// Gets the item containing the dragged data
				ClipData.Item item = event.getClipData().getItemAt(0);

				commentMsg += ((Button) v).getText() + " : ACTION_DROP"
						+ " - from " + item.getText().toString() + "\n";
				comments.setText(commentMsg);
//				if (v == buttonTarget) {
//					View dragView = (View) event.getLocalState();
//					((Button) v).setBackground(((Button) dragView)
//							.getBackground());
//					((Button) v).setText(((Button) dragView).getText());
//				}
				return true;
			case DragEvent.ACTION_DRAG_ENDED:
				if (event.getResult()) {
					commentMsg += ((Button) v).getText()
							+ " : ACTION_DRAG_ENDED - success.\n";
					comments.setText(commentMsg);
				} else {
					commentMsg += ((Button) v).getText()
							+ " : ACTION_DRAG_ENDED - fail.\n";
					comments.setText(commentMsg);
				}
				;
				return true;
			default: // unknown case
				commentMsg += ((Button) v).getText() + " : UNKNOWN !!!\n";
				comments.setText(commentMsg);
				return false;

			}

		}
	}

}