package cn.android.demo.apis.java.designmode;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

/**
 * 职责链模式
 * 
 * @author Elroy
 * 
 *         1.主要优点 职责链模式的主要优点如下：
 * 
 *         (1) 职责链模式使得一个对象无须知道是其他哪一个对象处理其请求，对象仅需知道该请求会被处理即可，接收者和发送者都没有对方的明确信息，
 *         且链中的对象不需要知道链的结构，由客户端负责链的创建，降低了系统的耦合度。 
 *         
 *         (2)请求处理对象仅需维持一个指向其后继者的引用，而不需要维持它对所有的候选处理者的引用，可简化对象的相互连接。 
 *         
 *         (3)在给对象分派职责时，职责链可以给我们更多的灵活性，可以通过在运行时对该链进行动态的增加或修改来增加或改变处理一个请求的职责。 
 *         
 *         (4)在系统中增加一个新的具体请求处理者时无须修改原有系统的代码，只需要在客户端重新建链即可，从这一点来看是符合“开闭原则”的。
 * 
 * 
 *         2.主要缺点
 * 
 *         职责链模式的主要缺点如下： 
 *         (1)由于一个请求没有明确的接收者，那么就不能保证它一定会被处理，该请求可能一直到链的末端都得不到处理；一个请求也可能因职责链没有被正确配置而得不到处理
 *         。 
 *         
 *         (2) 对于比较长的职责链，请求的处理可能涉及到多个处理对象，系统性能将受到一定影响，而且在进行代码调试时不太方便。 
 *         
 *         (3)如果建链不当，可能会造成循环调用，将导致系统陷入死循环。
 * 
 * 
 *         3.适用场景
 * 
 *         在以下情况下可以考虑使用职责链模式： 
 *         (1)有多个对象可以处理同一个请求，具体哪个对象处理该请求待运行时刻再确定，客户端只需将请求提交到链上
 *         ，而无须关心请求的处理对象是谁以及它是如何处理的。 
 *         
 *         (2) 在不明确指定接收者的情况下，向多个对象中的一个提交一个请求。 
 *         
 *         (3)可动态指定一组对象处理请求，客户端可以动态创建职责链来处理请求，还可以改变链中处理者之间的先后次序。
 * 
 */
public class ChainOfResponsibilityPattern extends Activity {
	Approver lxl;
	Approver jx;
	Approver szd;
	Approver meet;

	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tv = new TextView(this);
		tv.setMovementMethod(new ScrollingMovementMethod());
		setContentView(tv);

		lxl = new Director("李晓亮", tv);
		jx = new VicePresident("金鑫", tv);
		szd = new President("宋正东", tv);

		meet = new Congress("董事长", tv);

		lxl.setSuccessor(jx);
		jx.setSuccessor(szd);
		szd.setSuccessor(meet);

		// 创建采购清单
		PurchaseRequest pr1 = new PurchaseRequest(45000, 10001, "幌子");
		lxl.processRequest(pr1);

		PurchaseRequest pr2 = new PurchaseRequest(60000, 10002, "曙光女神");
		jx.processRequest(pr2);

		PurchaseRequest pr3 = new PurchaseRequest(160000, 10003, "猴子");
		szd.processRequest(pr3);

		PurchaseRequest pr4 = new PurchaseRequest(800000, 10004, "全套英雄");
		meet.processRequest(pr4);
	}
}

// 采购单：请求累
class PurchaseRequest {

	private double amount;// 采购金额
	private int number;// 采购单编号
	private String purpose;// 采购目的

	public PurchaseRequest(double amount, int number, String purpose) {
		this.amount = amount;
		this.number = number;
		this.purpose = purpose;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

}

// 审批者类：抽象处理者
abstract class Approver {

	protected Approver successor;// 定义后继对象
	protected String name;// 审批者姓名
	TextView text;

	public Approver(String name, TextView text) {
		this.name = name;
		this.text = text;
	}

	// 设置后继者
	public void setSuccessor(Approver successor) {
		this.successor = successor;
	}

	// 抽象请求处理方法
	public abstract void processRequest(PurchaseRequest request);
}

// 主任类：具体处理者
class Director extends Approver {

	public Director(String name, TextView text) {
		super(name, text);
	}

	@Override
	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < 50000) {
			// 请求处理
			Log.v("DDD",
					"主任" + this.name + " 审批采购批单：" + request.getNumber()
							+ ",金额：" + request.getAmount() + "元，采购目的："
							+ request.getPurpose());

			text.append("\n " + "主任 " + this.name + " 审批采购批单："
					+ request.getNumber() + ",金额：" + request.getAmount()
					+ "元，采购目的：" + request.getPurpose() + "\n");

		} else {
			this.successor.processRequest(request);// 转发请求
		}
	}

}

// 副董事长类:具体处理者
class VicePresident extends Approver {

	public VicePresident(String name, TextView text) {
		super(name, text);
		// TODO Auto-generated constructor stub
	}

	// 具体请求处理方法
	@Override
	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < 100000) {
			Log.v("DDD",
					"副董事长" + this.name + " 审批采购批单：" + request.getNumber()
							+ ",金额：" + request.getAmount() + "元，采购目的："
							+ request.getPurpose());
			text.append("\n " + "副董事长" + this.name + " 审批采购批单："
					+ request.getNumber() + ",金额：" + request.getAmount()
					+ "元，采购目的：" + request.getPurpose() + "\n");

		} else {
			this.successor.processRequest(request);// 转发请求
		}
	}

}

// 董事长类：具体处理者
class President extends Approver {

	public President(String name, TextView text) {
		super(name, text);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < 500000) {
			Log.v("DDD",
					"董事长" + this.name + " 审批采购批单：" + request.getNumber()
							+ ",金额：" + request.getAmount() + "元，采购目的："
							+ request.getPurpose());

			text.append("\n " + "董事长" + this.name + " 审批采购批单："
					+ request.getNumber() + ",金额：" + request.getAmount()
					+ "元，采购目的：" + request.getPurpose() + "\n");
		} else {
			this.successor.processRequest(request); // 转发请求
		}
	}

}

// 董事会类：具体处理者
class Congress extends Approver {

	public Congress(String name, TextView text) {
		super(name, text);
		// TODO Auto-generated constructor stub
	}

	// 具体请求处理方法
	public void processRequest(PurchaseRequest request) {
		Log.v("DDD",
				"召开董事会审批采购单：" + request.getNumber() + "，金额："
						+ request.getAmount() + "元，采购目的："
						+ request.getPurpose() + "。"); // 处理请求

		text.append("\n " + "召开董事会审批采购单 " + this.name + " 审批采购批单："
				+ request.getNumber() + ",金额：" + request.getAmount()
				+ "元，采购目的：" + request.getPurpose() + "\n");

	}
}