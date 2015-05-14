package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Spinner;

public class AndroidPopupWindow extends Activity {
	String[] DayOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday", "Saturday" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button btnOpenPopup = (Button) findViewById(R.id.pop_w_openpopup);
		btnOpenPopup.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				View popupView = layoutInflater.inflate(R.layout.popup, null);
				final PopupWindow popupWindow = new PopupWindow(popupView,
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

				Button btnDismiss = (Button) popupView
						.findViewById(R.id.pop_w_dismiss);

				Spinner popupSpinner = (Spinner) popupView
						.findViewById(R.id.pop_w_popupspinner);

				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						AndroidPopupWindow.this,
						android.R.layout.simple_spinner_item, DayOfWeek);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				popupSpinner.setAdapter(adapter);

				btnDismiss.setOnClickListener(new Button.OnClickListener() {

					@Override
					public void onClick(View v) {
						popupWindow.dismiss();
					}
				});

				popupWindow.showAsDropDown(btnOpenPopup, 50, -30);
				 popupView.setOnTouchListener(new OnTouchListener() {
				 int orgX;
				 int orgY;
				 int offsetX;
				 int offsetY;
				
				 @Override
				 public boolean onTouch(View v, MotionEvent event) {
				
				 switch (event.getAction()) {
				 case MotionEvent.ACTION_DOWN:
				 orgX = (int) event.getX();
				 orgY = (int) event.getY();
				
				 break;
				 case MotionEvent.ACTION_MOVE:
				 offsetX = (int) event.getRawX() - orgX;
				 offsetY = (int) event.getRawY() - orgY;
				 popupWindow.update(offsetX, offsetY, -1, -1, true);
				
				 break;
				 default:
				 break;
				 }
				
				 return true;
				 }
				 });

//				popupView.setOnTouchListener(new OnTouchListener() {
//					int orgX, orgY;
//					int offsetX, offsetY;
//
//					int orgWidth, orgHeight;
//
//					@Override
//					public boolean onTouch(View v, MotionEvent event) {
//						switch (event.getAction()) {
//						case MotionEvent.ACTION_DOWN:
//							orgX = (int) event.getRawX();
//							orgY = (int) event.getRawY();
//
//							orgWidth = v.getMeasuredWidth();
//							orgHeight = v.getMeasuredHeight();
//
//							break;
//						case MotionEvent.ACTION_MOVE:
//							offsetX = (int) event.getRawX() - orgX;
//							offsetY = (int) event.getRawY() - orgY;
//
//							// resize PopWindow
//							popupWindow.update(orgWidth + offsetX, orgHeight
//									+ offsetY);
//							break;
//						}
//						return true;
//					}
//				});
			}

		});
	}
}