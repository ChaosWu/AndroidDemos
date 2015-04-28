package cn.android.demo;

import android.app.Application;

/**
 * Created by Elroy on 2014/11/7.
 */
//
public class AppContext extends Application {
	/**
	 * <!-- All customizations that are NOT specific to a particular API-level
	 * can go here. -->
	 * <item name="android:windowIsTranslucent">true</item> <item
	 * name="android:windowBackground">@android:color/transparent</item>
	 */
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
