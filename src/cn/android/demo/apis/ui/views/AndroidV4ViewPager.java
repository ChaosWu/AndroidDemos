package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidV4ViewPager extends Activity {

	ViewPager viewPager;
	PagerTitleStrip pagerTitleStrip;
	MyPagerAdapter myPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		LinearLayout layout = new LinearLayout(this);
		layout.setPadding(100, 100, 100, 100);
		layout.setLayoutParams(layoutParams);

		LayoutParams viewPagerParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		viewPager = new ViewPager(this);
		viewPager.setLayoutParams(viewPagerParams);
		viewPager.setId(R.id.view_pager);

		LayoutParams titleParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		pagerTitleStrip = new PagerTitleStrip(this);
		pagerTitleStrip.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
		pagerTitleStrip.setTextColor(Color.BLUE);
		pagerTitleStrip.setLayoutParams(titleParams);

		viewPager.addView(pagerTitleStrip);
		layout.addView(viewPager);

		setContentView(layout);

		myPagerAdapter = new MyPagerAdapter();
		viewPager.setAdapter(myPagerAdapter);

	}

	public class MyPagerAdapter extends PagerAdapter {
		int numberOfPages = 5;
		int[] res = { android.R.drawable.ic_dialog_alert,
				android.R.drawable.ic_menu_camera,
				android.R.drawable.ic_menu_camera,
				android.R.drawable.ic_menu_directions,
				android.R.drawable.ic_menu_gallery };
		int[] backgroundcolor = { 0xFF101010, 0xFF202020, 0xFF303030,
				0xFF404040, 0xFF505050 };
		String[] title = { "Page 1", "pAge 2", "paGe 3", "pagE 4", "page 5" };

		@Override
		public int getCount() {
			return numberOfPages;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			TextView textView = new TextView(getBaseContext());
			textView.setTextColor(Color.WHITE);
			textView.setTextSize(30);
			textView.setTypeface(Typeface.DEFAULT_BOLD);
			textView.setText(String.valueOf(position));

			ImageView imageView = new ImageView(getBaseContext());
			imageView.setImageResource(res[position]);
			LayoutParams imageParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			imageView.setLayoutParams(imageParams);

			LinearLayout layout = new LinearLayout(getBaseContext());
			layout.setOrientation(LinearLayout.VERTICAL);

			LayoutParams layoutParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			layout.setBackgroundColor(backgroundcolor[position]);
			layout.setLayoutParams(layoutParams);
			layout.addView(textView);
			layout.addView(imageView);

			final int page = position;
			layout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(getBaseContext(),
							"Page:" + page + "  clicked", Toast.LENGTH_LONG)
							.show();
				}
			});
			container.addView(layout);

			return layout;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position, object);
			container.removeView((LinearLayout) object);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return title[position];
		}
	}

}