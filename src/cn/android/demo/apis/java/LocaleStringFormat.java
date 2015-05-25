package cn.android.demo.apis.java;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import cn.android.demo.apis.R;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;

public class LocaleStringFormat extends Activity {

	TextView textOut;
	TextView localeInfo;
	// 输入负数 android:inputType="numberSigned"
	EditText editText;

	Spinner spAvailableLocale;
	Locale[] availableLocales;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_locale_string_format);

		textOut = (TextView) findViewById(R.id.locale_string_format_printout);
		localeInfo = (TextView) findViewById(R.id.locale_string_format_localeinfo);
		editText = (EditText) findViewById(R.id.string_format_edit1);

		// get installed locales
		availableLocales = Locale.getAvailableLocales();
		spAvailableLocale = (Spinner) findViewById(R.id.locale_string_format_spavlocale);

		ArrayAdapter<Locale> adapter = new ArrayAdapter<Locale>(this,
				android.R.layout.simple_spinner_item, availableLocales);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spAvailableLocale.setAdapter(adapter);
		spAvailableLocale.setOnItemSelectedListener(onItemSelectedListener);
	}

	OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {

			String strEt = editText.getText().toString();
			int inInt;
			String str10 = null;
			String str8 = null;
			String str16 = null;
			if (!strEt.equals("")) {
				inInt = Integer.parseInt(strEt);
				str10 = String.format("parse to Integer\n(Decimal): %d\n",
						inInt);
				str8 = String.format("(Octal): %o\n", inInt);
				str16 = String.format("(hexadecimal): %x\n(HEXADECIMAL): %X\n",
						inInt, inInt);

			}

			Locale locale = (Locale) parent.getItemAtPosition(position);
			localeInfo
					.setText(String
							.format("DisplayCountry: %s\nDisplayLanguage: %s\nDisplayName: %s\n\n",
									locale.getDisplayCountry(),
									locale.getDisplayLanguage(),
									locale.getDisplayName()));

			Date now = new Date();
			textOut.setText(String.format(locale, "%tc\n", now) + // C library
																	// asctime(3)-like
																	// output.
					String.format(locale, "%tD\n", now) + // (MM/DD/YY)
					String.format(locale, "%tF\n", now) + // (YYYY-MM-DD)
					String.format(locale, "%tr\n", now) + // Full 12-hour time
					String.format(locale, "%tz\n", now) + // Time zone GMT
															// offset
					String.format(locale, "%tZ\n", now) // Localized time zone
														// abbreviation
			);

			// 使用DecimalFormat.getCurrencyInstance显示格式化货币
			NumberFormat numberFormat = DecimalFormat
					.getCurrencyInstance(locale);
			String strNum = numberFormat.format(12345.6789f);
			textOut.append("\n\n\n" + strNum);

			textOut.append("\n\n\n" + str10 + "\n" + str8 + "\n" + str16);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}

	};

}