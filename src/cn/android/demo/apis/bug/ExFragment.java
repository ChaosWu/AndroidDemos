package cn.android.demo.apis.bug;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myFragmentView = inflater.inflate(R.layout.ex_fragment_layout,
				container, false);
		 updateMyStatus("onCreateView()");
		return myFragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		 updateMyStatus("onActivityCreated()");
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		 updateMyStatus("onAttach()");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 updateMyStatus("onCreate()");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		 updateMyStatus("onDestroy()");
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		 updateMyStatus("onDestroyView()");
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		 updateMyStatus("onDetach()");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 updateMyStatus("onPause()");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 updateMyStatus("onResume()");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		 updateMyStatus("onStart()");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		 updateMyStatus("onStop()");
	}
	// public StatusListenter listenter;
	//
	// public interface StatusListenter {
	// void updateMyStatus(String myst);
	// }
	// ((AndroidExFragmentActivity) getActivity())
	// .updateStatus(" >> MyFragment: " + myst);

	private void updateMyStatus(String string) {
		AndroidExFragmentActivity.updateStatus(" >> MyFragment: " +string);
	}

	// ((AndroidExFragmentActivity)
	// getActivity()).updateStatus(" >> MyFragment: " + myst);

}