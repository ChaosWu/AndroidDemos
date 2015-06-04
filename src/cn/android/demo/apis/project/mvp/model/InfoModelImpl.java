package cn.android.demo.apis.project.mvp.model;
/**
 * Model具体实现
 * @author Elroy
 *
 */
public class InfoModelImpl implements IInfoModel{
	//模拟存储数据
	private InfoBean infoBean=new InfoBean();
	
	@Override
	public InfoBean getInfo() {
		//do something
		//模拟存储数据，真实有很多操作
		return infoBean;
	}

	@Override
	public void setInfo(InfoBean info) {
		//do something
		//模拟存储数据，真实有很多操作
		infoBean=info;		
	}
	

}
