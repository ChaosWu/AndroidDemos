package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

/**
 * 
 * 自动提示View
 * 
 * enoughToFilter ()： 当文本长度超过阈值时过滤
 * 
 * performValidation ()：代替 验证整个文本，这个子类方法验证每个单独的文字标记
 * 
 * setTokenizer (MultiAutoCompleteTextView.Tokenizer t)：用户正在输入时，
 * tokenizer设置将用于确定文本相关范围内
 * 
 * @author Elroy
 * 
 */
public class AndroidAutoCompleteTextView extends Activity implements
		TextWatcher {

	private AutoCompleteTextView autoCompleteTextView;
	private MultiAutoCompleteTextView multiAutoCompleteTextView;

	String item[] = { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_auto_complete_tv);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, item);

		autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.android_view_act);
		autoCompleteTextView.addTextChangedListener(this);
		autoCompleteTextView.setAdapter(adapter);

		multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.android_view_mact);
		multiAutoCompleteTextView.setAdapter(adapter);
		multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {

	}
}
