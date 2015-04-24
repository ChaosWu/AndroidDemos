package cn.android.demo.apis.ui.fragment;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * FragmentActivity 生命周期演示
 * 
 * @author Elroy
 * 
 */
public class AndroidV4FragmentActivity extends FragmentActivity {
	static public class MyFragment1 extends Fragment {

		String savedText;

		@Override
		public void onAttach(Activity activity) {
			toastFragment1Method();
			super.onAttach(activity);
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			toastFragment1Method();
			super.onCreate(savedInstanceState);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			toastFragment1Method();
			View view = inflater.inflate(R.layout.ui_fragment_fragmentlayout1, null);
			return view;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			toastFragment1Method();
			super.onActivityCreated(savedInstanceState);
		}

		@Override
		public void onStart() {
			toastFragment1Method();
			super.onStart();
		}

		@Override
		public void onResume() {
			toastFragment1Method();
			super.onResume();
		}

		@Override
		public void onPause() {
			toastFragment1Method();
			super.onPause();
		}

		@Override
		public void onStop() {
			toastFragment1Method();
			super.onStop();
		}

		@Override
		public void onDestroyView() {
			toastFragment1Method();
			super.onDestroyView();
		}

		@Override
		public void onDestroy() {
			toastFragment1Method();
			super.onDestroy();
		}

		@Override
		public void onDetach() {
			toastFragment1Method();
			super.onDetach();
		}

		private void toastFragment1Method() {
			StackTraceElement[] s = Thread.currentThread().getStackTrace();
			String methodName = s[3].getMethodName();
			Toast.makeText(getActivity(), "MyFragment1 : " + methodName,
					Toast.LENGTH_SHORT).show();

			Log.i("MYTAG", "MyFragment1 : " + methodName);
		}

	}

	FrameLayout fragmentContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		toastActivityMethod();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_fragment_android_fragmentactivity);

		fragmentContainer = (FrameLayout) findViewById(R.id.fl_fragmentactivity_container);
		if (savedInstanceState == null) {
			// if's the first time created
			MyFragment1 myListFragment1 = new MyFragment1();
			FragmentManager supportFragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = supportFragmentManager
					.beginTransaction();
			fragmentTransaction.add(R.id.fl_fragmentactivity_container, myListFragment1);
			fragmentTransaction.commit();
		}

	}

	@Override
	protected void onStart() {
		toastActivityMethod();
		super.onStart();
	}

	@Override
	protected void onResume() {
		toastActivityMethod();
		super.onResume();
	}

	@Override
	protected void onPause() {
		toastActivityMethod();
		super.onPause();
	}

	@Override
	protected void onStop() {
		toastActivityMethod();
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		toastActivityMethod();
		super.onDestroy();
	}

	private void toastActivityMethod() {
		StackTraceElement[] s = Thread.currentThread().getStackTrace();
		String methodName = s[3].getMethodName();
		Toast.makeText(getApplicationContext(), "MainActivity : " + methodName,
				Toast.LENGTH_SHORT).show();

		Log.i("MYTAG", "MainActivity : " + methodName);
	}

}
