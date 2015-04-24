package cn.android.demo.apis.bug;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class FragmentTestActivity extends FragmentActivity {
//
//	public static class MyFragment1 extends Fragment {
//
//		TextView textMsg;
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View view = inflater.inflate(R.layout.fragmentlayout_01, null);
//			textMsg = (TextView) view.findViewById(R.id.textmsg);
//
//			Bundle bundle = getArguments();
//			if (bundle != null) {
//				String msg = bundle.getString(KEY_MSG_1);
//				if (msg != null) {
//					textMsg.setText(msg);
//				}
//			}
//
//			return view;
//		}
//
//		public void setMsg(String msg) {
//			textMsg.setText(msg);
//		}
//
//	}
//
//	public static class MyFragment2 extends Fragment {
//
//		TextView textMsg;
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View view = inflater.inflate(R.layout.fragmentlayout_02, null);
//			textMsg = (TextView) view.findViewById(R.id.textmsg);
//
//			Bundle bundle = getArguments();
//			if (bundle != null) {
//				String msg = bundle.getString(KEY_MSG_2);
//				if (msg != null) {
//					textMsg.setText(msg);
//				}
//			}
//
//			return view;
//		}
//
//		public void setMsg(String msg) {
//			textMsg.setText(msg);
//		}
//
//	}
//
//	public static class MyFragment3 extends Fragment {
//
//		TextView textMsg;
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View view = inflater.inflate(R.layout.fragmentlayout_03, null);
//			textMsg = (TextView) view.findViewById(R.id.textmsg);
//
//			Bundle bundle = getArguments();
//			if (bundle != null) {
//				String msg = bundle.getString(KEY_MSG_3);
//				if (msg != null) {
//					textMsg.setText(msg);
//				}
//			}
//
//			return view;
//		}
//
//		public void setMsg(String msg) {
//			textMsg.setText(msg);
//		}
//
//	}
//
//	FrameLayout container;
//	FragmentManager myFragmentManager;
//	MyFragment1 myFragment1;
//	MyFragment2 myFragment2;
//	MyFragment3 myFragment3;
//	final static String TAG_1 = "FRAGMENT_1";
//	final static String TAG_2 = "FRAGMENT_2";
//	final static String TAG_3 = "FRAGMENT_3";
//	final static String KEY_MSG_1 = "FRAGMENT1_MSG";
//	final static String KEY_MSG_2 = "FRAGMENT2_MSG";
//	final static String KEY_MSG_3 = "FRAGMENT3_MSG";
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//		container = (FrameLayout) findViewById(R.id.maincontainer);
//
//		Button button1 = (Button) findViewById(R.id.button1);
//		Button button2 = (Button) findViewById(R.id.button2);
//		Button button3 = (Button) findViewById(R.id.button3);
//
//		button1.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//
//				MyFragment1 fragment = (MyFragment1) myFragmentManager
//						.findFragmentByTag(TAG_1);
//
//				if (fragment == null) {
//
//					Bundle bundle = new Bundle();
//					bundle.putString(KEY_MSG_1, "Replace MyFragment1");
//					myFragment1.setArguments(bundle);
//
//					FragmentTransaction fragmentTransaction = myFragmentManager
//							.beginTransaction();
//					fragmentTransaction.replace(R.id.maincontainer,
//							myFragment1, TAG_1);
//					fragmentTransaction.commit();
//
//				} else {
//
//					fragment.setMsg("MyFragment1 already loaded");
//				}
//			}
//		});
//
//		button2.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//
//				MyFragment2 fragment = (MyFragment2) myFragmentManager
//						.findFragmentByTag(TAG_2);
//
//				if (fragment == null) {
//
//					Bundle bundle = new Bundle();
//					bundle.putString(KEY_MSG_2, "Replace MyFragment2");
//					myFragment2.setArguments(bundle);
//
//					FragmentTransaction fragmentTransaction = myFragmentManager
//							.beginTransaction();
//					fragmentTransaction.replace(R.id.maincontainer,
//							myFragment2, TAG_2);
//					fragmentTransaction.commit();
//
//				} else {
//					fragment.setMsg("MyFragment2 already loaded");
//				}
//			}
//		});
//
//		button3.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//
//				MyFragment3 fragment = (MyFragment3) myFragmentManager
//						.findFragmentByTag(TAG_3);
//
//				if (fragment == null) {
//
//					Bundle bundle = new Bundle();
//					bundle.putString(KEY_MSG_3, "Replace MyFragment3");
//					myFragment3.setArguments(bundle);
//
//					FragmentTransaction fragmentTransaction = myFragmentManager
//							.beginTransaction();
//					fragmentTransaction.replace(R.id.maincontainer,
//							myFragment3, TAG_3);
//					fragmentTransaction.commit();
//
//				} else {
//					fragment.setMsg("MyFragment3 already loaded");
//				}
//			}
//		});
//
//		myFragmentManager = getSupportFragmentManager();
//		myFragment1 = new MyFragment1();
//		myFragment2 = new MyFragment2();
//		myFragment3 = new MyFragment3();
//
//		if (savedInstanceState == null) {
//			// if's the first time created
//
//			FragmentTransaction fragmentTransaction = myFragmentManager
//					.beginTransaction();
//			fragmentTransaction.add(R.id.maincontainer, myFragment1, TAG_1);
//			fragmentTransaction.commit();
//		}
//	}

}