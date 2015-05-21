package cn.android.demo.apis.java;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Set;

import cn.android.demo.apis.R;
import cn.android.demo.utils.ToastUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 货币
 * 
 * @author Elroy
 * 
 */
@SuppressLint("NewApi")
public class JavaCurrency extends Activity {

	ListView listCurrency;
	Set<Currency> availableCurrenciesSet;
	List<Currency> availableCurrenciesList;
	ArrayAdapter<Currency> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView listCurrency = new ListView(this);
		setContentView(listCurrency);

		// available from API Level 19
		availableCurrenciesSet = Currency.getAvailableCurrencies();

		availableCurrenciesList = new ArrayList<Currency>(
				availableCurrenciesSet);
		adapter = new ArrayAdapter<Currency>(this,
				android.R.layout.simple_list_item_1, availableCurrenciesList);
		listCurrency.setAdapter(adapter);

		listCurrency.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Currency currency = (Currency) parent
						.getItemAtPosition(position);
				String currencyCode = currency.getCurrencyCode();
				String displayName = currency.getDisplayName();
				String symbol = currency.getSymbol();

				// Toast.makeText(JavaCurrency.this,
				// displayName + "\n" + currencyCode + "\n" + symbol,
				// Toast.LENGTH_LONG).show();
				ToastUtil.showToast(JavaCurrency.this, displayName + "\n"
						+ currencyCode + "\n" + symbol);
			}
		});
	}
}