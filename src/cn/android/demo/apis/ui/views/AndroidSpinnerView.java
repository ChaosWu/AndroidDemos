package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class AndroidSpinnerView extends Activity {

	String[] text0 = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
			"Friday", "Saturday" };
	// ****************************************************
	String[] text1 = { "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY",
			"FRIDAY", "SATURDAY" };
	int[] val1 = { 0, 1, 2, 3, 4, 5, 6 };
	// ****************************************************
	// MyClass[] obj2 = { new MyClass("SUN", 0), new MyClass("MON", 1),
	// new MyClass("TUE", 2), new MyClass("WED", 3),
	// new MyClass("THU", 4), new MyClass("FRI", 5), new MyClass("SAT", 6) };

	MyClass[] obj2 = { new MyClass("$", "dollar sign"),
			new MyClass("¢", "cent sign"), new MyClass("£", "pound sign"),
			new MyClass("¤", "currency sign"), new MyClass("¥", "yen sign"),
			new MyClass("ƒ", "latin small letter f with hook"),
			new MyClass("", "afghani sign"),
			new MyClass("৲", "bengali rupee mark"),
			new MyClass("૱", "gujarati rupee sign"),
			new MyClass("௹", "tamil rupee sign"),
			new MyClass("฿", "thai currency symbol baht"),
			new MyClass("¤", "khmer currency symbol riel"),
			new MyClass("ℳ", "script capital m"),
			new MyClass("元", "cjk unified ideograph-5143"),
			new MyClass("円", "cjk unified ideograph-5186"),
			new MyClass("圆", "cjk unified ideograph-5706"),
			new MyClass("圓", "cjk unified ideograph-5713"),
			new MyClass("", "rial sign"),
			new MyClass("₠", "EURO-CURRENCY SIGN"),
			new MyClass("₡", "COLON SIGN"), new MyClass("₢", "CRUZEIRO SIGN"),
			new MyClass("₣", "FRENCH FRANC SIGN"),
			new MyClass("₤", "LIRA SIGN"), new MyClass("₥", "MILL SIGN"),
			new MyClass("₦", "NAIRA SIGN"), new MyClass("₧", "PESETA SIGN"),
			new MyClass("₨", "RUPEE SIGN"), new MyClass("₩", "WON SIGN"),
			new MyClass("₪", "NEW SHEQEL SIGN"), new MyClass("₫", "DONG SIGN"),
			new MyClass("€", "EURO SIGN"), new MyClass("₭", "KIP SIGN"),
			new MyClass("₮", "TUGRIK SIGN"), new MyClass("₯", "DRACHMA SIGN"),
			new MyClass("₰", "GERMAN PENNY SIGN"),
			new MyClass("₱", "PESO SIGN"), new MyClass("₲", "GUARANI SIGN"),
			new MyClass("₳", "AUSTRAL SIGN"), new MyClass("₴", "HRYVNIA SIGN"),
			new MyClass("₵", "CEDI SIGN"),
			new MyClass("₶", "LIVRE TOURNOIS SIGN"),
			new MyClass("₷", "SPESMILO SIGN"), new MyClass("₸", "TENGE SIGN"),
			new MyClass("₹", "INDIAN RUPEE SIGN"),
			new MyClass("₺", "TURKISH LIRA SIGN") };
	Spinner sp0;
	Spinner sp1;
	Spinner sp2;

	TextView tv0;
	TextView tv1;
	TextView tv2;

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_android_spinner_view);

		sp0 = $id(R.id.sp_return_value_spinner0);
		sp1 = $id(R.id.sp_return_value_spinner1);
		sp2 = $id(R.id.sp_return_value_spinner2);

		tv0 = $id(R.id.sp_return_value_text0);
		tv1 = $id(R.id.sp_return_value_text1);
		tv2 = $id(R.id.sp_return_value_text2);

		ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, text0);
		adapter0.setDropDownViewResource(android.R.layout.simple_spinner_item);
		sp0.setAdapter(adapter0);
		sp0.setOnItemSelectedListener(onItemSelectedListener0);

		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, text1);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(adapter1);
		sp1.setOnItemSelectedListener(onItemSelectedListener1);

		MySpinnerAdapter adapter2 = new MySpinnerAdapter(this,
				android.R.layout.simple_spinner_item, obj2);
		sp2.setAdapter(adapter2);
		sp2.setOnItemSelectedListener(onItemSelectedListener2);

	}

	private OnItemSelectedListener onItemSelectedListener0 = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			String str = (String) parent.getItemAtPosition(position);
			tv0.setText(str);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}
	};

	private OnItemSelectedListener onItemSelectedListener1 = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			String str = String.valueOf(val1[position]);
			tv1.setText(str);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}
	};
	private OnItemSelectedListener onItemSelectedListener2 = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			MyClass obj = (MyClass) parent.getItemAtPosition(position);

			// String str = String.valueOf(obj.getValue());
			String str = String.valueOf(obj.getStringValue());
			tv2.setText(str);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}
	};

	public class MySpinnerAdapter extends ArrayAdapter<MyClass> {
		private Context context;
		private MyClass[] myClasses;

		public MySpinnerAdapter(Context context, int resource,
				MyClass[] myClasses) {
			super(context, resource);
			this.context = context;
			this.myClasses = myClasses;
		}

		public int getCount() {
			return myClasses.length;
		}

		public MyClass getItem(int position) {
			return myClasses[position];
		}

		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView label = new TextView(context);
			label.setText(myClasses[position].getText());
			return label;
		}

		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			TextView label = new TextView(context);
			label.setText(myClasses[position].getText());
			return label;
		}

	}

	public class MyClass {
		private int value;
		private String text;
		private String strValue;

		public MyClass(String text, int value) {
			this.text = text;
			this.value = value;
		}

		public MyClass(String text, String strValue) {
			this.text = text;
			this.strValue = strValue;
		}

		public void setText(String str) {
			this.text = str;
		}

		public String getText() {
			return text;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getStringValue() {
			return strValue;
		}

		public int getValue() {
			return value;
		}
	}
}
