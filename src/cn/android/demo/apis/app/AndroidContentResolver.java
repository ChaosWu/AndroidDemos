package cn.android.demo.apis.app;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.widget.TextView;
/**
 * this == AndroidContentResolver==tv.getContext();
 * 
 * this!=getApplicationContext() != getBaseContext();
 * @author Elroy
 *
 */
public class AndroidContentResolver extends Activity {

	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tv = new TextView(this);
		setContentView(tv);

		ContentResolver contentResolverA = getContentResolver();

		// same ContentResolver returned
		// ContentResolver contentResolverB = AnotherClass
		// .tryGetContentResolver(this);

		// ContentResolver contentResolverB =
		// AnotherClass.tryGetContentResolver(MainActivity.this);

		// ContentResolver contentResolverB =
		// AnotherClass.tryGetContentResolver(tv.getContext());

		// ContentResolver contentResolverB =
		// AnotherClass.tryGetContentResolver(getBaseContext());

		
		ContentResolver contentResolverB = AnotherClass
				.tryGetContentResolver(getApplicationContext());
		
		String msg = "this: \n" + this + "\n\n"
				+ "AndroidContentResolver.this:\n"
				+ AndroidContentResolver.this + "\n\n" + "tv.getContext():\n"
				+ tv.getContext() + "\n\n" + "getBaseContext():\n"
				+ getBaseContext() + "\n\n" + "getApplicationContext():\n"
				+ getApplicationContext() + "\n\n\n";

		if (contentResolverA == contentResolverB) {
			msg += "contentResolverA == contentResolverB\n" + contentResolverB;
		} else {
			msg += "contentResolverA != contentResolverB\n" + contentResolverA
					+ "\n" + contentResolverB;
		}

		tv.setText(msg);
	}
}
