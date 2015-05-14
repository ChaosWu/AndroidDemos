package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 抽屉demo
 * 
 * V4侧滑
 * 
 * @author Elroy
 * 
 */
public class AndroidDrawerLayout extends Activity {

	DrawerLayout drawerLayout;
	View drawerView;
	TextView tvPrompt;
	TextView tvPrompt2;
	TextView tvSelection;

	ListView drawerList;

	private String[] dayOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday", "Saturday" };
	ArrayAdapter<String> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_android_drawer_layout);

		tvPrompt = $id(R.id.drawer_layout_prompt);
		tvPrompt2 = $id(R.id.drawer_layout_prompt2);
		tvSelection = $id(R.id.drawer_layout_selection);

		drawerLayout = $id(R.id.drawer_layout);
		drawerView = $id(R.id.drawer_layout_start);

		Button btOpenDrawer = $id(R.id.drawer_layout_opendrawer);
		btOpenDrawer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				drawerLayout.openDrawer(drawerView);
			}
		});

		Button btCloseDrawer = $id(R.id.drawer_layout_closedrawer);
		btCloseDrawer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				drawerLayout.closeDrawers();
			}
		});

		drawerLayout.setDrawerListener(mDrawerListener);
		drawerView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});

		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dayOfWeek);

		drawerList = $id(R.id.drawer_layout_list);
		drawerList.setAdapter(arrayAdapter);

		drawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String sel = (String) parent.getItemAtPosition(position);
				tvSelection.setText(sel);
			}
		});

	}

	DrawerListener mDrawerListener = new DrawerListener() {

		@Override
		public void onDrawerStateChanged(int newState) {
			String state;
			switch (newState) {
			case DrawerLayout.STATE_IDLE:
				state = "STATE_IDLE";
				break;
			case DrawerLayout.STATE_DRAGGING:
				state = "STATE_DRAGGING";
				break;

			case DrawerLayout.STATE_SETTLING:
				state = "STATE_SETTLING";
				break;
			default:
				state = "unknown!";
			}
			tvPrompt2.setText(state);
		}

		@Override
		public void onDrawerSlide(View drawerView, float slideOffset) {
			tvPrompt.setText("onDrawerSlide"
					+ String.format("%.2f", slideOffset));
		}

		@Override
		public void onDrawerOpened(View arg0) {
			tvPrompt.setText("onDrawerOpened");
		}

		@Override
		public void onDrawerClosed(View arg0) {
			tvPrompt.setText("onDrawerClosed");
		}
	};

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}
}
