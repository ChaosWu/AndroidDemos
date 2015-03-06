package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class AndroidCustomGridView extends Activity {
	private Integer[] mThumbIds = { R.drawable.chaoswu, R.drawable.chaoswu,
			R.drawable.chaoswu, R.drawable.chaoswu, R.drawable.chaoswu,
			R.drawable.chaoswu, R.drawable.chaoswu, R.drawable.chaoswu,
			R.drawable.chaoswu, R.drawable.chaoswu, R.drawable.chaoswu,
			R.drawable.chaoswu, R.drawable.chaoswu, R.drawable.chaoswu,
			R.drawable.chaoswu, R.drawable.chaoswu };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_view_gridview);

		GridView gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(new MyAdapter(this));

	}

	public class MyAdapter extends BaseAdapter {
		private Context context;

		public MyAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			return mThumbIds.length;
		}

		@Override
		public Object getItem(int position) {
			return mThumbIds[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View grid;
			if (convertView == null) {
				grid = new View(context);
				LayoutInflater inflater = getLayoutInflater();
				grid = inflater.inflate(R.layout.ui_view_gridview_item, parent,
						false);
			} else {
				grid = convertView;
			}

			ImageView imageView = (ImageView) grid
					.findViewById(R.id.iv_gridview_item);
			imageView.setImageResource(mThumbIds[position]);

			TextView textView = (TextView) grid
					.findViewById(R.id.tv_gridview_item);
			textView.setText(String.valueOf(position));
			return grid;
		}

	}

}
