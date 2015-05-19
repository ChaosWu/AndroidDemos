package cn.android.demo.apis.other.lifecycle;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 生命周期
 * 
 * @author Elroy
 * 
 */
public class AndroidLifecycle extends Activity {
	Button btMain;
	Button btMainFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_android_lifecycle);

		btMain = (Button) findViewById(R.id.bt_life_main_activity);
		btMainFragment = (Button) findViewById(R.id.bt_life_main_fragment_activity);
		btMain.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AndroidLifecycle.this,
						MainActivity.class);
				startActivity(intent);
			}
		});
		btMainFragment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AndroidLifecycle.this,
						MainFragmentActivity.class);
				startActivity(intent);
			}
		});
	}
}
