package cn.android.demo.apis.ui.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * View的事件传递
 * 
 * //TODO View处理事件流程图
 * 
 * Activity.dispatchTouchEvent()----> ViewGroup.dispatchTouchEvent()
 * ---->View.dispatchTouchEvent()---->View.onTouchEvent()
 * 
 * 事件从上往下执行（Activity-->ViewGroup-->View），若最底层子View没有消费事件，事件向上传递，最后由Activity.OnTouchEvent()函数执行。
 * 
 * onInterceptTouchEvent停止向下传递消息，若View没有对ACTION_DOWN进行消费，之后的其他事件也不会传递
 * 
 *  
 * 
 * @author Elroy
 * 
 */
public class ViewGroupDemo extends ViewGroup {

	public ViewGroupDemo(Context context) {
		super(context);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub

	}

	// 消费
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	// 传递
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		return super.dispatchTouchEvent(event);
	}

	// 拦截
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev);
	}
}
