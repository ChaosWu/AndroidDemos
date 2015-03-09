package cn.android.demo.apis;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.EncodingUtils;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {
	public final static String TAG = MainActivity.class.getSimpleName();
	WakeLock wakeLock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(TAG, "onCreate   ******   ");
		// http://android-developers.blogspot.com/2011/03/identifying-app-installations.html
		// 获取唯一标识，在2.2不是100%正确
		Log.v("DDD",
				"Android ID:"
						+ Settings.Secure.getString(getContentResolver(),
								Settings.Secure.ANDROID_ID));
		Log.v("DDD", new String(EncodingUtils.getAsciiBytes("A")));

		Intent intent = getIntent();
		String path = intent.getStringExtra(Config.PATH);

		if (path == null) {
			path = "";
		}

		setListAdapter(new SimpleAdapter(this, getData(path),
				android.R.layout.simple_list_item_1, new String[] { "title" },
				new int[] { android.R.id.text1 }));
		getListView().setTextFilterEnabled(true);

		wakeLock();
	}
	
	private void wakeLock() {
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
				"Full Wake Lock");
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

//		wakeLock.acquire();
		wakeLock.acquire(1000);//持锁，timeout 自动释放
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.v(TAG, "onPause   ******   ");
		wakeLock.release();
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
	}

}
