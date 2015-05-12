package cn.android.demo.apis.app;

import java.util.List;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView.ScaleType;

/**
 * 通过 PackageManager 获取所有app 图标
 * 
 * @author Elroy
 * 
 */
public class AndroidPackageManager extends Activity {

	PackageManager packageManager;

	GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_package_manager_info);
		packageManager = getPackageManager();

		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);

		List<ResolveInfo> infos = packageManager.queryIntentActivities(intent,
				0);

		gridView = (GridView) findViewById(R.id.gv_package_manager);
		gridView.setAdapter(new MyBaseAdapter(this, infos));

		gridView.setOnItemClickListener(itemClickListener);
	}

	public class MyBaseAdapter extends BaseAdapter {
		private Context mContext;
		private List<ResolveInfo> mInfos;

		public MyBaseAdapter(Context c, List<ResolveInfo> list) {
			mContext = c;
			mInfos = list;
		}

		@Override
		public int getCount() {
			return mInfos.size();
		}

		@Override
		public Object getItem(int position) {
			return mInfos.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView iv;
			if (convertView == null) {
				iv = new ImageView(mContext);
				iv.setLayoutParams(new GridView.LayoutParams(85, 85));
				iv.setScaleType(ScaleType.CENTER_CROP);
				iv.setPadding(8, 8, 8, 8);
			} else {
				iv = (ImageView) convertView;
			}

			ResolveInfo resolveInfo = mInfos.get(position);
			iv.setImageDrawable(resolveInfo.loadIcon(packageManager));
			return iv;
		}

	}

	private OnItemClickListener itemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ResolveInfo cInfo = (ResolveInfo) parent
					.getItemAtPosition(position);
			ActivityInfo activityInfo = cInfo.activityInfo;

			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			intent.setClassName(activityInfo.packageName, activityInfo.name);

			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

			startActivity(intent);

		}
	};
}
