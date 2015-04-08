package cn.android.demo.apis.bug;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;

@SuppressLint("NewApi")
public class NavigationTabsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab tabA = actionBar.newTab();
		tabA.setText("Tab A");
//		tabA.setTabListener(new TabListener<Fragment>(this, "Tab A",
//				MyFragmentA.class));

	}

	public static class TabListener<T extends Fragment> implements
			ActionBar.TabListener {

		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClz;

		public TabListener(Activity activity, String tag, Class<T> clz) {
			this.mActivity = activity;
			this.mTag = tag;
			this.mClz = clz;
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {

			// Check if the fragment is already initialized
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

	}
}
