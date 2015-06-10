package cn.android.demo.apis.java.designmode;

import java.util.Hashtable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * 享元模式：运用共享技术有效滴支持大量细粒度对象的复用，系列只 使用少量的对象，而这些对象都很相似，状态变化很小，可以实现对
 * 象的多次复用，由于享元模式要求能够共享的对象必须 是细粒度对象， 因此它又称为轻量级模式，它是一种对象结构型模式
 * 
 * @author Elroy
 * 
 */
public class FlyweightPattern extends Activity {
	public static TextView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new TextView(this);
		setContentView(view);

		IgoChessman black1, black2, black3, white1, white2;
		IgoChessmanFactory factory;

		// 获取享元工厂对象
		factory = IgoChessmanFactory.getInstance();

		black1 = factory.getIgoChessman("b");
		black2 = factory.getIgoChessman("b");
		black3 = factory.getIgoChessman("b");

		Log.v("DDD", "判断两颗黑子是否相同：" + (black1 == black2));

		white1 = factory.getIgoChessman("w");
		white2 = factory.getIgoChessman("w");
		Log.v("DDD", "判断两颗白子是否相同：" + (white1 == white2));

		black1.display();
		black2.display();
		black3.display();
		white1.display();
		white2.display();
		
		
		 //显示棋子，同时设置棋子的坐标位置  
        black1.display(new Coordinates(1,2));  
        black2.display(new Coordinates(3,4));  
        black3.display(new Coordinates(1,3));  
        white1.display(new Coordinates(2,5));  
        white2.display(new Coordinates(2,4));  

	}

}

// 围棋棋子 类：抽象享元类
abstract class IgoChessman {
	public abstract String getColor();

	public void display() {
		FlyweightPattern.view.append("\n棋子颜色：" + this.getColor());
		Log.v("DDD", "棋子颜色：" + this.getColor());
	}

	public void display(Coordinates coord) {
		FlyweightPattern.view.append("\n棋子颜色：" + this.getColor() + "，棋子位置："
				+ coord.getX() + "，" + coord.getY());
		Log.v("DDD", "棋子颜色：" + this.getColor() + "，棋子位置：" + coord.getX() + "，"
				+ coord.getY());

	}
}

// 黑色棋子类：具体享元类
class BlackIgoChessman extends IgoChessman {

	@Override
	public String getColor() {
		return "黑色";
	}
}

// 白色棋子类：具体享元类
class WhiteIgoChessman extends IgoChessman {
	public String getColor() {
		return "白色";
	}
}

// 围棋棋子工厂类：享元工厂类，使用单例模式进行设计
class IgoChessmanFactory {

	private static IgoChessmanFactory instance = new IgoChessmanFactory();
	private static Hashtable<String, IgoChessman> ht;// 使用Hashtable来存储享元对象，充当享元池

	private IgoChessmanFactory() {
		ht = new Hashtable<String, IgoChessman>();
		WhiteIgoChessman white;
		BlackIgoChessman black;
		white = new WhiteIgoChessman();
		ht.put("w", white);
		black = new BlackIgoChessman();
		ht.put("b", black);
	}

	// 返回享元工厂类的唯一实例
	public static IgoChessmanFactory getInstance() {
		return instance;
	}

	// 通过Key来获取存储在Hashtable 中的享元对象
	public static IgoChessman getIgoChessman(String color) {
		return ht.get(color);
	}

}

// 坐标类：外部状态类
class Coordinates {
	private int x;
	private int y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
