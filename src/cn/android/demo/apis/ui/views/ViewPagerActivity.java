package cn.android.demo.apis.ui.views;

import java.util.ArrayList;

import cn.android.demo.apis.R;
import cn.android.demo.apis.ui.fragment.FragmentA;
import cn.android.demo.apis.ui.fragment.FragmentB;
import cn.android.demo.apis.ui.fragment.FragmentC;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;

//Null
@SuppressLint("NewApi")
public class ViewPagerActivity extends ActionBarActivity {

	ViewPager viewPager;
	TabsAdapter mTabsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewPager = new ViewPager(this);
		viewPager.setId(R.id.view_pager);
		setContentView(viewPager);

		final ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

		mTabsAdapter = new TabsAdapter(this, viewPager);
		mTabsAdapter.addTab(bar.newTab().setText("Fragment A"),
				FragmentA.class, null);

		mTabsAdapter.addTab(bar.newTab().setText("Fragment B"),
				FragmentB.class, null);

		mTabsAdapter.addTab(bar.newTab().setText("Fragment C"),
				FragmentC.class, null);

		if (savedInstanceState != null) {
			bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));

		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// super.onSaveInstanceState(outState);
		outState.putInt("tab", getActionBar().getSelectedNavigationIndex());

	}

	void switchFragment(int target) {
		viewPager.setCurrentItem(target);
	}

	public static class TabsAdapter extends FragmentPagerAdapter implements
			TabListener, OnPageChangeListener {
		private final Context mContext;
		private final ActionBar mActionBar;
		private final ViewPager mPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

		static final class TabInfo {
			private final Class<?> clz;
			private final Bundle args;

			public TabInfo(Class<?> _class, Bundle _args) {
				this.clz = _class;
				this.args = _args;
			}
		}

		public TabsAdapter(ActionBarActivity activity, ViewPager viewPager) {
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mActionBar = activity.getSupportActionBar();
			mPager = viewPager;
			mPager.setAdapter(this);
			mPager.setOnPageChangeListener(this);

		}

		public void addTab(Tab tab, Class<?> clz, Bundle args) {
			TabInfo info = new TabInfo(clz, args);
			tab.setTag(info);
			tab.setTabListener(this);

			mTabs.add(info);
			mActionBar.addTab(tab);
			notifyDataSetChanged();

		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {

		}

		@Override
		public void onPageSelected(int position) {
			mActionBar.setSelectedNavigationItem(position);
		}

		@Override
		public Fragment getItem(int position) {

			TabInfo info = mTabs.get(position);

			return Fragment
					.instantiate(mContext, info.clz.getName(), info.args);
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		@Override
		public void onTabReselected(Tab arg0,
				android.support.v4.app.FragmentTransaction arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabSelected(Tab arg0,
				android.support.v4.app.FragmentTransaction arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabUnselected(Tab arg0,
				android.support.v4.app.FragmentTransaction arg1) {
			// TODO Auto-generated method stub

		}

	}
}