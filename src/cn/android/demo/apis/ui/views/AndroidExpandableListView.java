package cn.android.demo.apis.ui.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.android.demo.apis.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidExpandableListView extends Activity {
	ExpandableListView expandableListView;
	MyExpandableListAdapter adapter;
	List<String> groupList;
	HashMap<String, List<String>> childMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_android_expandable_listview);
		init();
		expandableListView = (ExpandableListView) findViewById(R.id.android_expandable_lv_mylist);
		adapter = new MyExpandableListAdapter(this, groupList, childMap);
		expandableListView.setAdapter(adapter);

	}

	private void init() {
		groupList = new ArrayList<String>();
		childMap = new HashMap<String, List<String>>();

		List<String> groupList0 = new ArrayList<String>();
		groupList0.add("groupList0 - 1");
		groupList0.add("groupList0 - 2");
		groupList0.add("groupList0 - 3");

		List<String> groupList1 = new ArrayList<String>();
		groupList1.add("groupList1 - 1");
		groupList1.add("groupList1 - 2");
		groupList1.add("groupList1 - 3");

		List<String> groupList2 = new ArrayList<String>();
		groupList2.add("groupList2 - 1");
		groupList2.add("groupList2 - 2");
		groupList2.add("groupList2 - 3");

		List<String> groupList3 = new ArrayList<String>();
		groupList3.add("groupList3 - 1");
		groupList3.add("groupList3 - 2");
		groupList3.add("groupList3 - 3");

		groupList.add("Group List 0");
		groupList.add("Group List 1");
		groupList.add("Group List 2");
		groupList.add("Group List 3");

		childMap.put(groupList.get(0), groupList0);
		childMap.put(groupList.get(1), groupList1);
		childMap.put(groupList.get(2), groupList2);
		childMap.put(groupList.get(3), groupList3);

	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		Drawable drawable_groupIndicator = getResources().getDrawable(
				R.drawable.anf_ok);
		int drawable_width = drawable_groupIndicator.getMinimumWidth();

		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
			expandableListView.setIndicatorBounds(expandableListView.getWidth()
					- drawable_width, expandableListView.getWidth());
		} else {
			expandableListView.setIndicatorBoundsRelative(
					expandableListView.getWidth() - drawable_width,
					expandableListView.getWidth());
		}

		Toast.makeText(
				AndroidExpandableListView.this,
				"drawable_width: " + drawable_width + "\n"
						+ "expandableListView.getWidth()"
						+ expandableListView.getWidth(), Toast.LENGTH_LONG)
				.show();
	}

	public class MyExpandableListAdapter extends BaseExpandableListAdapter {

		private Context context;
		private List<String> listGroup;
		private HashMap<String, List<String>> listChild;

		public MyExpandableListAdapter(Context context, List<String> listGroup,
				HashMap<String, List<String>> listChild) {
			this.context = context;
			this.listGroup = listGroup;
			this.listChild = listChild;
		}

		@Override
		public int getGroupCount() {
			return listGroup.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return listChild.get(listGroup.get(groupPosition)).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return listGroup.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return listChild.get(listGroup.get(groupPosition)).get(
					childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater
						.inflate(
								R.layout.ui_view_android_expandable_listview_group_layout,
								null);
			}

			String textGroup = (String) getGroup(groupPosition);

			TextView textViewGroup = (TextView) convertView
					.findViewById(R.id.android_expandable_lv_group);
			textViewGroup.setText(textGroup);

			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater
						.inflate(
								R.layout.ui_view_android_expandable_listview_item_layout,
								null);
			}

			TextView textViewItem = (TextView) convertView
					.findViewById(R.id.android_expandable_lv_item);

			String text = (String) getChild(groupPosition, childPosition);

			textViewItem.setText(text);
			return convertView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return false;
		}

	}
}
