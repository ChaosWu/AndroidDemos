package cn.android.demo.apis.java.designmode;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SingletonActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView view = new TextView(this);
		setContentView(view);
		String st1 = Singleton.getInstance().sayHello();
		String st2 = SingletonEnum.INSTANCE.sayHello();
		view.setText(st1);
		view.append("\n");
		view.append(st2);
	}
}
