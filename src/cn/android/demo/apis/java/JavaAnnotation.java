package cn.android.demo.apis.java;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Java 注解使用方法(TODO 没掌握)
 * 
 * 注解：能够添加到java源代码的语法元数据，类，方法，变量，参数，包都可以被注解，
 * 
 * 可用来将信息元数据与程序元素进行关联
 * 
 * 作用： 1、标记，用于告诉编译器一些信息
 * 
 * 2、编译时动态处理，如动态生成代码
 * 
 * 3、运行时动态处理，如得到注解信息
 * 
 * @author Elroy
 * 
 */
public class JavaAnnotation extends Activity {
	@FruitName(date = "获得数据")
	String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		setContentView(tv);
		tv.setText(name);
		Log.v("DDD", "hehe" + name);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v("DDD", "hehe" + name);
	}
}
