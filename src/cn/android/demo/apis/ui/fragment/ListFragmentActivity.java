package cn.android.demo.apis.ui.fragment;

import cn.android.demo.apis.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 研究 v4的Fragment用法
 * 
 * @author Elroy
 * 
 */
@SuppressLint("NewApi")
public class ListFragmentActivity extends Activity {
	static String[] month = { "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December" };
	static boolean isSinglePane;
	final static String TAG_1 = "FRAGMENT_1";
	final static String TAG_2 = "FRAGMENT_2";
	MyDetailFragment detailFragment;
	MyListFragment listFragment;

	public static class MyListFragment extends ListFragment {
		@Override
		public void onActivityCreated(@Nullable Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);

			ListAdapter adapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, month);
			setListAdapter(adapter);
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {

			String clickedDetail = (String) l.getItemAtPosition(position);
			// MyDetailFragment detailFragment = new MyDetailFragment();
			// Bundle bundle = new Bundle();
			// bundle.putString("KEY_DETAIL", clickedDetail);
			// detailFragment.setArguments(bundle);
			// Log.v("DDD", clickedDetail);

			// TODO 增加Fragment 的返回功能
			/*
			 * Add this transaction to the back stack. This means that the
			 * transaction will be remembered after it is committed, and will
			 * reverse its operation when later popped off the stack.
			 */
			// fragmentTransaction.addToBackStack(null);
			// fragmentTransaction.commit();
			// getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

			MyDetailFragment myDetailFragment = (MyDetailFragment) getFragmentManager()
					.findFragmentById(R.id.detail_fragment);
			myDetailFragment.updateDetail(clickedDetail);

		}
	}

	public static class MyDetailFragment extends Fragment {

		TextView tv;

		@Override
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container,
				@Nullable Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.ui_fragment_detail, null);
			tv = (TextView) view.findViewById(R.id.tv_title_detailfragment);
			// Bundle bundle = getArguments();
			// if (bundle != null) {
			// String detail = bundle.getString("KEY_DETAIL",
			// "no argument pass");
			// tv.setText(detail);
			// }

			return view;
		}

		public void updateDetail(String detail) {
			tv.setText(detail);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_fragment_listfragment_test_1);

		// myFragmentManager = getFragmentManager();
		// detailFragment = new MyDetailFragment();
		// listFragment = new MyListFragment();

	}
}
