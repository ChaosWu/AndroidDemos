package cn.android.demo.apis.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

/**
 * 反射 runtime 时候才知道 名称的类，获取其完整的构造，并生成对象实体，或调用方法
 * 
 * @author Elroy
 * 
 */
public class JavaReflect extends Activity {

	Class c;
	Constructor[] constructors;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		// 设置TextView 自动滚动
		tv.setMovementMethod(new ScrollingMovementMethod());
		tv.setText("获取数据中");
		// 调用反射
		//changeRSSFeed(tv);

		getConstructor(tv);
		getDeclaredFields(tv);
		getDeclaredMethods(tv);
		setContentView(tv);
		
	}

	// private void changeRSSFeed(TextView tv) {
	// try {
	// // 实例化对象
	// Class[] p = new Class[2];
	// p[0] = String.class;
	// p[1] = String.class;
	//
	// Constructor ccc = c.getConstructor(p);
	// Object object = ccc.newInstance("这是标题", "这是详情介绍");
	//
	// // 修改成员变量
	// Field field = c.getField("title");
	// field.set(object, "这是标题 被改了");
	// // 调用类方法
	// Method method = c.getMethod("getTitleAndDes");
	// Object object2 = method.invoke(object);
	// tv.append("\n 调用反射方法 修改 数据：" + object2);
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	/**
	 * 获取方法
	 * 
	 * @param tv
	 */
	private void getDeclaredMethods(TextView tv) {
		Method[] method = c.getDeclaredMethods();
		tv.append("\n\n 反射：方法");
		for (int i = 0; i < method.length; i++) {

			tv.append("\n--->>第" + i + "个方法：" + method[i].toString());
			tv.append("\n--->>方法名：" + method[i].getName());
			tv.append("\n--->>修饰符：" + method[i].getModifiers());
			tv.append("\n--->>返回值：" + method[i].getReturnType());
			Class p[] = method[i].getParameterTypes();
			for (int j = 0; j < p.length; j++) {
				tv.append("\n--->>参数" + j + ":" + p[j]);

			}
		}
	}

	/**
	 * 获取成员变量
	 * 
	 * @param tv
	 */
	private void getDeclaredFields(TextView tv) {
		Field[] fields = c.getDeclaredFields();
		tv.append("\n\n 反射：获取成员变量");
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			tv.append("\n--->>变量名：" + f.getName());
			tv.append("\n--->>变量类型:" + f.getModifiers());
			tv.append("\n--->>修饰符：" + f.getType());

		}
	}

	/**
	 * 获取构造函数
	 * 
	 * @param tv
	 */
	private void getConstructor(TextView tv) {
		try {
			c = Class.forName("cn.android.demo.apis.project.rss.RSSFeed");
			constructors = c.getConstructors();
			for (int i = 0; i < constructors.length; i++) {
				// Log.v("DDD", "反射");
				// Log.v("DDD", "==第" + i + "个方法==" +
				// constructors[i].toString());
				// Log.v("DDD", "--->>方法名：" + constructors[i].getName());
				// Log.v("DDD", "--->>修饰符：" + constructors[i].getModifiers());
				tv.append("\n 反射：获取构造函数");
				tv.append("\n==第" + i + "个方法==" + constructors[i].toString());
				tv.append("\n--->>方法名：" + constructors[i].getName());
				tv.append("\n--->>修饰符：" + constructors[i].getModifiers());

				Class<?> p[] = constructors[i].getParameterTypes();
				for (int j = 0; j < p.length; j++) {
					tv.append("\n 参数" + j + ":" + p[j]);
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
