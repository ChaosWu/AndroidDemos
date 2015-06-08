package cn.android.demo.apis.java.designmode;

/**
 * 单例设计模式
 * 
 * @author Elroy
 * 
 */
public class Singleton {
	private static Singleton singleton = null;
	private static final byte[] lock = new byte[0];

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (singleton == null) {
			synchronized (lock) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}

		}
		return singleton;
	}

	public String sayHello() {
		return this.getClass().getSimpleName();
	}
}
