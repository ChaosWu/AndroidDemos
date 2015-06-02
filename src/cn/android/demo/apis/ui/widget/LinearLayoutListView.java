package cn.android.demo.apis.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;

@SuppressLint("NewApi")
public class LinearLayoutListView extends LinearLayout {
	public LinearLayoutListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ListView listView;

	public LinearLayoutListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public LinearLayoutListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void setListView(ListView lv) {
		listView = lv;
	}

}
