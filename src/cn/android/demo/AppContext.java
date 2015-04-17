package cn.android.demo;

import android.app.Application;

/**
 * Created by Elroy on 2014/11/7.
 */ 
//
public class AppContext extends Application {
	 
	private static AppContext context;
	// 该类不能被实例化
	// private AppContext() {
	// context = this;
	// }

	@Override
	public void onCreate() {
		context = this;
		super.onCreate();
	}

	public static AppContext getApplication() {
		return context;
	}

}
