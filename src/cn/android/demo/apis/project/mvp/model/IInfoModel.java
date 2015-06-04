package cn.android.demo.apis.project.mvp.model;

/**
 * Model层抽象接口，方便解耦
 * 
 * @author Elroy
 * 
 */
public interface IInfoModel {
	// 从数据提供者获取数据方法
	InfoBean getInfo();
	
	//存入数据提供者方法
	void setInfo(InfoBean bean);
}
