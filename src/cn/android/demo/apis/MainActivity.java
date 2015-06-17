package cn.android.demo.apis;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.EncodingUtils;

import cn.android.demo.utils.TimeUtil;

import android.app.ActivityManager;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {
	public final static String TAG = MainActivity.class.getSimpleName();
	public static SparseArray<String> sparseArray = new SparseArray<String>();
	static {
		sparseArray.append(VERSION_CODES.BASE,
				"The original, first, version of Android.");
		sparseArray.append(VERSION_CODES.BASE_1_1,
				"First Android update, officially called 1.1");
		sparseArray.append(VERSION_CODES.CUPCAKE, "Android 1.5");
		sparseArray.append(VERSION_CODES.CUR_DEVELOPMENT,
				"Magic version number...");
		sparseArray.append(VERSION_CODES.DONUT, "Android 1.6");
		sparseArray.append(VERSION_CODES.ECLAIR, "Android 2.0");
		sparseArray.append(VERSION_CODES.ECLAIR_0_1, "Android 2.0.1");
		sparseArray.append(VERSION_CODES.ECLAIR_MR1, "Android 2.1");
		sparseArray.append(VERSION_CODES.FROYO, "Android 2.2");
		sparseArray.append(VERSION_CODES.GINGERBREAD, "Android 2.3");
		sparseArray.append(VERSION_CODES.GINGERBREAD_MR1, "Android 2.3.3");
		sparseArray.append(VERSION_CODES.HONEYCOMB, "Android 3.0");
		sparseArray.append(VERSION_CODES.HONEYCOMB_MR1, "Android 3.1");
		sparseArray.append(VERSION_CODES.HONEYCOMB_MR2, "Android 3.2");
		sparseArray.append(VERSION_CODES.ICE_CREAM_SANDWICH, "Android 4.0");
		sparseArray.append(VERSION_CODES.ICE_CREAM_SANDWICH_MR1,
				"Android 4.0.3");
		sparseArray.append(VERSION_CODES.JELLY_BEAN, "Android 4.1");
		sparseArray.append(VERSION_CODES.JELLY_BEAN_MR1, "Android 4.2");
		sparseArray.append(VERSION_CODES.JELLY_BEAN_MR2, "Android 4.3");
		sparseArray.append(VERSION_CODES.KITKAT, "Android 4.4");

	}

	// WakeLock wakeLock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		// setProgressBarIndeterminateVisibility(true);
		String weekDate = TimeUtil.getWeekOfDate(new Date());
		Log.v("DDD", "今天" + weekDate);
		View title = getWindow().findViewById(android.R.id.title);
		View titleBar = (View) title.getParent();
		titleBar.setBackgroundColor(Color.GREEN);
		int version = Build.VERSION.SDK_INT;
		Log.v(TAG, "onCreate   ******   Version:" + sparseArray.get(version));

		// http://android-developers.blogspot.com/2011/03/identifying-app-installations.html
		// 获取唯一标识，在2.2不是100%正确
		Log.v("DDD",
				"Android ID:"
						+ Settings.Secure.getString(getContentResolver(),
								Settings.Secure.ANDROID_ID));
		Log.v("DDD", new String(EncodingUtils.getAsciiBytes("A")));

		int memClass = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE))
				.getMemoryClass();
		Log.v("DDD", "memClass:" + memClass);

		Intent intent = getIntent();
		String path = intent.getStringExtra(Config.PATH);

		if (path == null) {
			path = "";
		}

		setListAdapter(new SimpleAdapter(this, getData(path),
				android.R.layout.simple_list_item_1, new String[] { "title" },
				new int[] { android.R.id.text1 }));
		getListView().setTextFilterEnabled(true);

		// wakeLock();

		// // TODO 获取icon坐标位置 无效
		// Rect rect =this.getIntent().getSourceBounds();
		// Log.v("DDD", "top:" + rect.top + "\n" + "right:" + rect.right + "\n"
		// + "bottom:" + rect.bottom + "\n" + "left:" + rect.left + "\n"
		//
		// );
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.v("DDD", "onSaveInstanceState");

	}

	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
		Log.v("DDD", "onRestoreInstanceState");

	}

	// ****************************************************
	// TODO 横竖屏切换 == 获取数据
	// final MyData data=(MyData)getLastNonConfigurationInstance();

	// TODO 横竖屏切换 ==保存数据
	// @Override
	// public Object onRetainNonConfigurationInstance() {
	// MyData data=数据;
	// return data;
	// }

	// Fragment 用 setRetainInstance 代替
	// onActivityCreated 判断是否存在数据
	// onCreate() 加上 setRetainInstance(true)，故而可以在横竖屏切换时不被重新创建和重复执行异步任务

	// ****************************************************

	// TODO
	// private void wakeLock() {
	// //忧伤
	// PowerManager powerManager = (PowerManager)
	// getSystemService(Context.POWER_SERVICE);
	// wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
	// "Full Wake Lock");
	// }

	// 时调用活动检测到用户的按的键。默认实现简单地完成当前的活动,但您可以覆盖这个做任何你想要的。
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Log.v("DDD", "onBackPressed");
	}

	@Override
	protected void onStart() {
		super.onStart();
		PackageInfo info;
		String strVersion;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
			strVersion = "Version Name:" + info.versionName + "\n"
					+ "Version Code:" + String.valueOf(info.versionCode);

			Log.v("DDD", "******" + strVersion);
			// 操作系统的名称：操作系统的版本
			Log.v("DDD", "******" + System.getProperty("os.name") + ":"
					+ System.getProperty("os.version"));

		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.v(TAG, "onStart   ******   ");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.v(TAG, "onRestart   ******   ");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v(TAG, "onResume   ******   ");

		// wakeLock.acquire();
		// wakeLock.acquire(1000);//持锁，timeout 自动释放
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.v(TAG, "onPause   ******   ");
		// wakeLock.release();
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.v(TAG, "onStop   ******   ");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v(TAG, "onDestroy   ******   ");
	}

	/**
	 * 
	 * @param prefix
	 *            前缀
	 * 
	 * @return
	 */
	private List<Map<String, Object>> getData(String prefix) {

		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Config.CATEGORY_SAMPLE_CODE);

		PackageManager pm = getPackageManager();

		List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

		if (list == null) {
			return myData;
		}
		String[] prefixPath;
		String prefixWithSlash = prefix;

		if (prefix.equals("")) {
			prefixPath = null;
		} else {
			prefixPath = prefix.split("/");
			prefixWithSlash = prefix + "/";
		}

		int len = list.size();

		Map<String, Boolean> entries = new HashMap<String, Boolean>();

		for (int i = 0; i < len; i++) {
			ResolveInfo info = list.get(i);
			CharSequence labelSeq = info.loadLabel(pm);
			Log.v(TAG, "labelSqe:    " + labelSeq.toString());
			Log.v(TAG, "info.name:   " + info.activityInfo.name);
			String label = labelSeq != null ? labelSeq.toString()
					: info.activityInfo.name;

			if (prefixWithSlash.length() == 0
					|| label.startsWith(prefixWithSlash)) {
				String[] labelPath = label.split("/");
				String nextLabel = prefixPath == null ? labelPath[0]
						: labelPath[prefixPath.length];

				if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
					addItem(myData,
							nextLabel,
							activityIntent(
									info.activityInfo.applicationInfo.packageName,
									info.activityInfo.name));

				} else {
					if (entries.get(nextLabel) == null) {
						addItem(myData, nextLabel,
								browseIntent(prefix.equals("") ? nextLabel
										: prefix + "/" + nextLabel));
						entries.put(nextLabel, true);
					}
				}

			}

		}
		Collections.sort(myData, sDisplayNameComparator);
		return myData;
	}

	private final static Comparator<Map<String, Object>> sDisplayNameComparator = new Comparator<Map<String, Object>>() {
		private final Collator collator = Collator.getInstance();

		public int compare(Map<String, Object> map1, Map<String, Object> map2) {
			return collator.compare(map1.get("title"), map2.get("title"));
		}
	};

	protected Intent browseIntent(String path) {
		Intent result = new Intent();
		result.setClass(this, MainActivity.class);
		result.putExtra(Config.PATH, path);
		return result;
	}

	protected Intent activityIntent(String pkg, String componentName) {
		Intent result = new Intent();

		result.setClassName(pkg, componentName);
		return result;
	}

	protected void addItem(List<Map<String, Object>> data, String name,
			Intent intent) {
		Log.v(TAG, "name:    " + name);
		Log.v(TAG, "intent:    " + intent.getPackage());
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("title", name);
		temp.put("intent", intent);
		data.add(temp);
	}

	/**
	 * l：列表视图，单击发生 v:点击列表视图 position：在列表中查看的位置 id:点击的行id
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> map = (HashMap<String, Object>) l
				.getItemAtPosition(position);
		Intent intent = (Intent) map.get("intent");
		startActivity(intent);
		// TODO Activity 直接切换动画
		 overridePendingTransition(R.anim.flip_in, R.anim.flip_out);

		// // 增加2015/06/17
		// overridePendingTransition(0, 0);

		// Fragment切换动画
		// fragmentTransaction.setCustomAnimations(R.anim.flip_in,
		// R.anim.flip_out)
	}

}
