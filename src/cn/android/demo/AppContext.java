package cn.android.demo;

import android.app.Application;
import android.util.DisplayMetrics;

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
	
//	// Instantiates a DisplayMetrics object
//			DisplayMetrics localDisplayMetrics = new DisplayMetrics();
//
//			// Gets the current display metrics from the current Window
//			getActivity().getWindowManager().getDefaultDisplay()
//					.getMetrics(localDisplayMetrics);

}
