package cn.android.demo.apis.project.mvp.presenter;

import cn.android.demo.apis.project.mvp.model.IInfoModel;
import cn.android.demo.apis.project.mvp.model.InfoBean;
import cn.android.demo.apis.project.mvp.model.InfoModelImpl;
import cn.android.demo.apis.project.mvp.view.IInfoView;

public class Presenter {
	private IInfoModel infoModel;
	private IInfoView infoView;

	public Presenter(IInfoView infoView) {
		this.infoView = infoView;
		infoModel = new InfoModelImpl();
	}

	// 供UI调用
	public void saveInfo(InfoBean bean) {
		infoModel.setInfo(bean);
	}

	// 供UI调用
	public void getInfo() {
		// 通过调用IInfoView的方法来更新显示，设计模式 类似回调 监听处理
		infoView.setInfo(infoModel.getInfo());
	}
}
