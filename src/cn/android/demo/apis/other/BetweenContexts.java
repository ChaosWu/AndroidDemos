package cn.android.demo.apis.other;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * BetweenContexts.this vs getApplicationContext()
 * 
 * 上下文生命周期：
 * BetweenContexts.this：        当前页面销毁-重新创建
 * 
 * getApplicationContext()：当整个程序kill- 重新启动
 * 
 * 
 * @author Elroy
 * 
 */
public class BetweenContexts extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView tv = new TextView(this);
		setContentView(tv);
		String s1 = "MainActivity.this:\n" + "getClass() = "
				+ BetweenContexts.this.getClass() + "\n" + BetweenContexts.this;

		String s2 = "getApplicationContext():\n" + "getClass() = "
				+ getApplicationContext().getClass() + "\n"
				+ getApplicationContext();
		tv.setText(s1);
		tv.append("\n\n" + s2);
	}
}
