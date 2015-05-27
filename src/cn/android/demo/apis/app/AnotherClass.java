package cn.android.demo.apis.app;

import android.content.ContentResolver;
import android.content.Context;

public class AnotherClass {
	static public ContentResolver tryGetContentResolver(Context c) {
		ContentResolver contentResolver = c.getContentResolver();
		return contentResolver;

	}
}
