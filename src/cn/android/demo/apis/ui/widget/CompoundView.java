package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;

/**
 * 创建--->复合View
 * 
 * 提高重用性
 * 
 * @author Elroy
 * 
 */

public class CompoundView extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_widget_compound_view);

		SidespinnerView fruitsSpinner = (SidespinnerView) findViewById(R.id.my_spv_fruits);
		CharSequence fruitList[] = { "Apple", "Orange", "Pear", "Grapes" };

		fruitsSpinner.setValues(fruitList);
	}
	
	
	
	
}
