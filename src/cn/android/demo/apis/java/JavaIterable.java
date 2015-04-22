package cn.android.demo.apis.java;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * 迭代器用法
 * 
 * @author Elroy
 * 
 */
public class JavaIterable extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);

		TextView tv1 = new TextView(this);
		tv1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

		TextView tv2 = new TextView(this);
		tv2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

		layout.addView(tv1);
		layout.addView(tv2);

		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("Sunday");
		arrayList.add("Monday");
		arrayList.add("Tuesday");
		arrayList.add("Wednesday");
		arrayList.add("Thursday");
		arrayList.add("Friday");
		arrayList.add("Saturday");

		String result_for = "";
		for (String item : arrayList) {
			result_for += item + "\n";
		}

		Iterator<String> iterator=arrayList.iterator();
		String result_while="";
		while (iterator.hasNext()) {
				result_while+=iterator.next()+"\n";
		}
		
		tv1.setText(result_for);
		tv2.setText(result_while);
	}
}
