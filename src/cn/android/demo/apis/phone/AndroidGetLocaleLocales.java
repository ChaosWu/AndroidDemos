package cn.android.demo.apis.phone;

import java.text.SimpleDateFormat;
import java.util.Locale;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AndroidGetLocaleLocales extends Activity {

	TextView tvDefaultLocale;
	TextView tvNumOfLocale;

	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_get_locale_locales);
		tvDefaultLocale = (TextView) findViewById(R.id.tv_defaultlocale);
		tvNumOfLocale = (TextView) findViewById(R.id.tv_numberoflocale);

		listView = (ListView) findViewById(R.id.lv_listviewlocale);

		Locale defaultLocale = Locale.getDefault();
		tvDefaultLocale.setText("Default Locale:" + defaultLocale.toString());

		final Locale[] availableLocales = Locale.getAvailableLocales();
		tvNumOfLocale.setText("Number of available Locale:"
				+ availableLocales.length);

		String[] availableLocalesString = new String[availableLocales.length];
		for (int i = 0; i < availableLocalesString.length; i++) {
			availableLocalesString[i] = availableLocales[i].toString();
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, availableLocalesString);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Locale item = availableLocales[position];

				String format = "yyyy-MM-dd'T' HH:mm:ss.SSSZ";
				SimpleDateFormat dateFormat = new SimpleDateFormat(format, item);

				java.util.Date date = new java.util.Date();

				ToastUtil.showToast(
						getApplicationContext(),
						"Language: " + item.getLanguage() + "\n" + "Country: "
								+ item.getCountry() + "\n" + "DisplayName: "
								+ item.getDisplayName() + "\n"
								+ "DisplayLanguage: "
								+ item.getDisplayLanguage() + "\n"
								+ "DisplayCountry: " + item.getDisplayCountry()
								+ "\n" + dateFormat.format(date)

				);

			}
		});

	}
}
