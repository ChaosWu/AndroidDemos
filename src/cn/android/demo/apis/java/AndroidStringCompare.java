package cn.android.demo.apis.java;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * java基础 ，String比较
 * 
 * @author Elroy
 * 
 */
public class AndroidStringCompare extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_string_compare);
		TextView Result = (TextView) findViewById(R.id.tv_result);
		TextView Result1 = (TextView) findViewById(R.id.tv_result1);
		TextView Result2 = (TextView) findViewById(R.id.tv_result2);
		TextView Result3 = (TextView) findViewById(R.id.tv_result3);
		TextView Result4 = (TextView) findViewById(R.id.tv_result4);
		TextView Result5 = (TextView) findViewById(R.id.tv_result5);
		TextView Result6 = (TextView) findViewById(R.id.tv_result6);
		TextView Result7 = (TextView) findViewById(R.id.tv_result7);
		TextView Result8 = (TextView) findViewById(R.id.tv_result8);
		TextView Result9 = (TextView) findViewById(R.id.tv_result9);
		TextView Result10 = (TextView) findViewById(R.id.tv_result10);

		String s1 = "hello";
		String s2 = "hello";
		String s3 = s1;
		String s4 = "h" + "e" + "l" + "l" + "o";
		String s5 = new String("hello");
		String s6 = new String(new char[] { 'h', 'e', 'l', 'l', 'o' });

		Result.setText("String s1 = \"hello\""
				+ "\n"
				+ "String s2 = \"hello\""
				+ "\n"
				+ "String s3 = s1;"
				+ "\n"
				+ "String s4 = \"h\" + \"e\" + \"l\" + \"l\" + \"o\""
				+ "\n"
				+ "String s5 = new String(\"hello\")"
				+ "\n"
				+ "String s6 = new String(new char[] { 'h', 'e', 'l', 'l', 'o' })"

		);

		Result1.setText("s1==s2: "+s1 + " == " + s2 + ": " + (s1 == s2));
		Result2.setText("s1.equals(s2): "+s1 + " equals " + s2 + ": " + (s1.equals(s2)));
		Result3.setText("s1==s3: "+s1 + " == " + s3 + ": " + (s1 == s3));
		Result4.setText("s1.equals(s3): "+s1 + " equals " + s3 + ": " + (s1.equals(s3)));
		Result5.setText("s1 == s4: "+s1 + " == " + s4 + ": " + (s1 == s4));
		Result6.setText("s1.equals(s4): "+s1 + " equals " + s4 + ": " + (s1.equals(s4)));
		Result7.setText("s1 == s5: "+s1 + " == " + s5 + ": " + (s1 == s5));
		Result8.setText("s1.equals(s5): "+s1 + " equals " + s5 + ": " + (s1.equals(s5)));
		Result9.setText("s1 == s6: "+s1 + " == " + s6 + ": " + (s1 == s6));
		Result10.setText("s1.equals(s6): "+s1 + " equals " + s6 + ": " + (s1.equals(s6)));
	}
}