package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidV4ViewPager2 extends Activity {

	ViewPager viewPager;
	MyPagerAdapter myPagerAdapter;
	private TextView textMsg;
	private Button btnToFirst;
	private Button btnToLast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_android_v4_viewpager2);
		textMsg = (TextView) findViewById(R.id.v4_viewpage2_msg);
		textMsg.setMovementMethod(new ScrollingMovementMethod());

		btnToFirst = (Button) findViewById(R.id.v4_viewpage2_tofirst);
		btnToLast = (Button) findViewById(R.id.v4_viewpage2_tolast);

		viewPager = (ViewPager) findViewById(R.id.v4_viewpage2_myviewpager);
		myPagerAdapter = new MyPagerAdapter();
		viewPager.setAdapter(myPagerAdapter);

		viewPager.setOnPageChangeListener(myOnPageChangeListener);

		btnToFirst.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(0);
			}
		});
		btnToLast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int indexLast = viewPager.getAdapter().getCount() - 1;
				viewPager.setCurrentItem(indexLast);
			}
		});
	}

	OnPageChangeListener myOnPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageScrollStateChanged(int state) {
			// Called when the scroll state changes.
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// This method will be invoked when the current page is scrolled,
			// either as part of a programmatically initiated smooth scroll
			// or a user initiated touch scroll.
		}

		@Override
		public void onPageSelected(int position) {
			// This method will be invoked when a new page becomes selected.
			textMsg.append("onPageSelected:" + position + "\n");
		}
	};

	private class MyPagerAdapter extends PagerAdapter {

		int NumberOfPages = 5;

		int[] res = { android.R.drawable.ic_dialog_alert,
				android.R.drawable.ic_menu_camera,
				android.R.drawable.ic_menu_compass,
				android.R.drawable.ic_menu_directions,
				android.R.drawable.ic_menu_gallery };
		int[] backgroundcolor = { 0xFF101010, 0xFF202020, 0xFF303030,
				0xFF404040, 0xFF505050 };

		@Override
		public int getCount() {
			return NumberOfPages;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			textMsg.append("instantiateItem:" + position + "\n");

			TextView textView = new TextView(AndroidV4ViewPager2.this);
			textView.setTextColor(Color.WHITE);
			textView.setTextSize(30);
			textView.setTypeface(Typeface.DEFAULT_BOLD);
			textView.setText(String.valueOf(position));

			ImageView imageView = new ImageView(AndroidV4ViewPager2.this);
			imageView.setImageResource(res[position]);
			LayoutParams imageParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			imageView.setLayoutParams(imageParams);

			LinearLayout layout = new LinearLayout(AndroidV4ViewPager2.this);
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
					Toast.makeText(AndroidV4ViewPager2.this,
							"Page " + page + " clicked", Toast.LENGTH_LONG)
							.show();
				}
			});

			container.addView(layout);
			return layout;
		}

		//
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((LinearLayout) object);
			textMsg.append("destroyItem:" + position + "\n");
		}

	}
}