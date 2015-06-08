package cn.android.demo.apis.java.designmode;

public enum SingletonEnum {
	INSTANCE;
	public static SingletonEnum getInstance() {
		return INSTANCE;
	}

	public String sayHello() {
		return this.getClass().getSimpleName();
	}
}
