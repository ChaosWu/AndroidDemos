package cn.android.demo.apis.project.mvp.view;

import cn.android.demo.apis.project.mvp.model.InfoBean;

/**
 * View层的抽象接口
 * 
 * @author Elroy
 * 
 */
public interface IInfoView {
	/** 给UI显示数据的方法 */
	void setInfo(InfoBean info);

	/** 从UI取数据的方法 */
	InfoBean getInfo();
}
