package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class AndroidRadioGroup extends Activity {
	TextView checkedId;
	TextView checkedIndex;

	RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_android_gradiogroup);

		checkedId = (TextView) findViewById(R.id.tv_rg_checkedid);
		checkedIndex = (TextView) findViewById(R.id.tv_rg_checkedindex);

		radioGroup = (RadioGroup) findViewById(R.id.rg_radiogroup);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				RadioButton button = (RadioButton) radioGroup
						.findViewById(checkedId);
				int checkedIndex = radioGroup.indexOfChild(button);
				AndroidRadioGroup.this.checkedId.setText("checkedID = " + checkedId);
				AndroidRadioGroup.this.checkedIndex.setText("checkedIndex = " + checkedIndex);
			}
		});
	}
}
