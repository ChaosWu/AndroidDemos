package cn.android.demo.apis.thread;

import java.util.LinkedList;
import java.util.Random;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 解决生产者-消费者问题
 * 
 * 在计算中，生产者-消费者问题（也称为有界缓冲区问题） 是一个多进程同步问题的典型例子
 * 
 * 问题描述： 生产者和消费者，谁分享用作队列共同的，固定大小的缓冲区。生产者-数据，把它放入缓冲区，并重新开始。消费者-从缓冲器取出它。
 * 
 * @author Elroy
 * 
 */
public class ThreadWaitNotify extends Activity {

	TextView infoProducer;
	TextView infoConsumer;

	String msgProducer;// 生产者
	String msgConsumer;// 消费者

	Button btStart;

	ShareClass shareObj = new ShareClass();
	long startingTime;

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_android_synchronized_methods);

		infoProducer = $id(R.id.share_object_between_thread_synchronized_method_infoa);
		infoConsumer = $id(R.id.share_object_between_thread_synchronized_method_infob);
		btStart = $id(R.id.share_object_between_thread_synchronized_method_buttonstart);
		btStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				msgProducer = "Producer\n";
				msgConsumer = "Consumer\n";

				Thread threadProducer = new Thread(new Runnable() {

					@Override
					public void run() {
						int ele = 0;
						Random random = new Random();

						while (true) {
							String strEle = String.valueOf(ele);
							msgProducer += strEle + "\n";

							ThreadWaitNotify.this.runOnUiThread(new Runnable() {

								@Override
								public void run() {
									infoProducer.setText(msgProducer);
								}
							});

							shareObj.produce(String.valueOf(ele));
							long randomDelay = 500 + random.nextInt(1000);
							SystemClock.sleep(randomDelay);
							ele++;
						}
					}
				});

				Thread threadConsumer = new Thread(new Runnable() {

					@Override
					public void run() {
						while (true) {
							Random random = new Random();
							while (true) {
								msgConsumer += shareObj.consume() + "\n";

								ThreadWaitNotify.this
										.runOnUiThread(new Runnable() {

											@Override
											public void run() {
												infoConsumer
														.setText(msgConsumer);
											}
										});

								long randomDelay = 500 + random.nextInt(1000);
								SystemClock.sleep(randomDelay);
							}
						}
					}
				});

				startingTime = System.currentTimeMillis();
				threadProducer.start();
				threadConsumer.start();
			}
		});
	}

	private class ShareClass {
		final int BUFFER_MAX = 5;
		LinkedList<String> buffer;

		public ShareClass() {
			buffer = new LinkedList<String>();
		}

		// 生产
		public synchronized void produce(String element) {
			while (buffer.size() == BUFFER_MAX) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 将element添加双向链表末尾
			buffer.offer(element);
			notifyAll();
		}

		// 消费
		public synchronized String consume() {
			while (buffer.size() == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 删除并返回第一个节点
			String element = buffer.poll();
			Log.v("DDD", "element:" + element);
			notifyAll();
			return element;
		}
	}

}
