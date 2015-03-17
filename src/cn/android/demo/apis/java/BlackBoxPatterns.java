package cn.android.demo.apis.java;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 黑箱简单设计模式
 * 
 * @author Elroy
 * 
 */
public class BlackBoxPatterns extends Activity {

	private TextView tvStuA;
	private TextView tvStuB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_black_box_patterns);

		tvStuA = (TextView) findViewById(R.id.tv_student_tuition_a);
		tvStuB = (TextView) findViewById(R.id.tv_student_tuition_b);

		Student stu1 = new Student();
		Student stu2 = new Student();
		
		ITuition da = new DaXueTuition();
		ITuition xiao = new XiaoXueTuition();

		stu1.setTuition(da);
		stu2.setTuition(xiao);

		tvStuA.append(stu1.computerTuition() + "RMB");
		tvStuB.append(stu2.computerTuition() + "RMB");

	}

	/** 计费接口 */
	/**
	 * 
	 * @return 返回具体费用
	 */
	private interface ITuition {
		public float getValue();
	}

	private class DaXueTuition implements ITuition {

		@Override
		public float getValue() {
			return 10000f;
		}

	}

	private class XiaoXueTuition implements ITuition {

		@Override
		public float getValue() {
			return 100f;
		}

	}

	private class Student {
		private String name;
		// 每个学生有自己的计算学费标准
		private ITuition tuition;

		// 传递一个计算学费标准
		public void setTuition(ITuition tuition) {
			this.tuition = tuition;
		}

		// 具体计算学费由接口实现
		public float computerTuition() {
			return tuition.getValue();
		}

	}

}
