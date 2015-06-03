package cn.android.demo.apis.java;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
//1. 拨打一个电话：
//
//am start -a android.intent.action.CALL -d tel:10086
//
//这里-a表示动作，-d表述传入的数据，还有-t表示传入的类型。
//
//2. 打开一个网页：
//
//am start -a android.intent.action.VIEW -d  http://www.baidu.com （这里-d表示传入的data）
//
//3. 打开音乐播放器：
//
//am start -a android.intent.action.MUSIC_PLAYER 或者
//包名和应用名可以在Androidmanifest.xml文件查看到
//am start -n com.android.music/om.android.music.MusicBrowserActivity
//
//4. 启动一个服务：
//
//am startservice <服务名称>
//
//例如：am startservice -n com.android.music/com.android.music.MediaPlaybackService (这里-n表示组件)
//或者   am startservice -a com.smz.myservice (这里-a表示动作，就是你在Androidmanifest里定义的) 
//
//5. 发送一个广播：
//
//am broadcast -a <广播动作>
//
//例如： am broadcast -a com.smz.mybroadcast

/**
 * 未执行
 *  
 * @author Elroy
 *
 */
public class AdbAM extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		Button button = new Button(this);
		button.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		button.setText("执行AM打电话");

		layout.addView(button);
		setContentView(layout);

		cmdAM();
		// button.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// }
		// });

	}

	private void cmdAM() {
//		String[] command = { "am", "start", "-a", "android.intent.action.CALL",
//				"-d", "tel:18516891869" };

		
		String[] command={"am","start -a android.intent.action.VIEW -d  http://www.baidu.com"};
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(command);
			processBuilder.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
