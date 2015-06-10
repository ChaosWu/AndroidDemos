package cn.android.demo.apis.java;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Java 注解使用方法
 * 
 * @author Elroy
 * 
 */
public class JavaAnnotation extends Activity {
	@FruitName("我是注解")
	String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		setContentView(tv);
		tv.setText(name);
		Log.v("DDD","hehe"+ name);
	}
}
