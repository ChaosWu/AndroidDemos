package cn.android.demo.apis.project.mvp.view;

import cn.android.demo.apis.R;
import cn.android.demo.apis.project.mvp.model.InfoBean;
import cn.android.demo.apis.project.mvp.presenter.Presenter;
import cn.android.demo.utils.ToastUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements IInfoView,
		OnClickListener {
	private Presenter presenter;
	private EditText inputId, inputName, inputAddr;
	private Button saveBtn, loadBtn;
	private TextView infoTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project_mvp_main_activity);
		init();
	}

	private void init() {
		presenter = new Presenter(this);
		inputId = (EditText) findViewById(R.id.pro_mvp_id_input);
		inputName = (EditText) findViewById(R.id.pro_mvp_name_input);
		inputAddr = (EditText) findViewById(R.id.pro_mvp_addr_input);
		saveBtn = (Button) findViewById(R.id.pro_mvp_input_confirm);
		loadBtn = (Button) findViewById(R.id.pro_mvp_get_confirm);
		infoTxt = (TextView) findViewById(R.id.pro_mvp_show);

		saveBtn.setOnClickListener(this);
		loadBtn.setOnClickListener(this);
	}

	@Override
	public void setInfo(InfoBean info) {
		StringBuilder builder = new StringBuilder("");
		builder.append(info.getId());
		builder.append("\n");
		builder.append(info.getName());
		builder.append("\n");
		builder.append(info.getAddress());

		infoTxt.setText(builder.toString());
	}

	@Override
	public InfoBean getInfo() {
		InfoBean infoBean = new InfoBean();
		int id = 0;

		// ********抛出异常或者对字符串检测*********
		// if (!inputId.getText().toString().matches("-?\\d+")) {
		// ToastUtil.showToast(this, "包含非法数字");
		// }

		try {
			id = Integer.parseInt(inputId.getText().toString());
		} catch (NumberFormatException e) {
			ToastUtil.showToast(this, "包含非法数字");
		}
		infoBean.setId(id);
		infoBean.setName(inputName.getText().toString());
		infoBean.setAddress(inputAddr.getText().toString());
		return infoBean;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pro_mvp_input_confirm:
			presenter.saveInfo(getInfo());
			break;
		case R.id.pro_mvp_get_confirm:
			presenter.getInfo();
			break;
		default:
			break;
		}
	}
}
