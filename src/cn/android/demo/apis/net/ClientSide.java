package cn.android.demo.apis.net;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import org.apache.http.protocol.HttpService;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 客户端，启动一个线程连接Server下载文件
 * 
 * @author Elroy
 * 
 *         client_side_
 */
public class ClientSide extends Activity {
	EditText editTextAddress;
	Button buttonConnect;
	TextView textPort;

	static final int SOCKET_SERVER_PORT = 8080;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.net_android_client_side);
		editTextAddress = (EditText) findViewById(R.id.client_side_address);
		textPort = (TextView) findViewById(R.id.client_side_port);
		textPort.setText("port: " + SOCKET_SERVER_PORT);
		buttonConnect = (Button) findViewById(R.id.client_side_connect);

		buttonConnect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ClientRxThread clientRxThread = new ClientRxThread(
						editTextAddress.getText().toString(),
						SOCKET_SERVER_PORT);

				clientRxThread.start();
			}
		});

	}

	private class ClientRxThread extends Thread {
		String dstAddress;
		int dstPort;

		ClientRxThread(String address, int port) {
			dstAddress = address;
			dstPort = port;
		}

		@Override
		public void run() {
			Socket socket = null;

			try {
				socket = new Socket(dstAddress, dstPort);

				File file = new File(Environment.getExternalStorageDirectory(),
						"MyHead.png");
				// 对象
				ObjectInputStream ois = new ObjectInputStream(
						socket.getInputStream());

				byte[] bytes;
				FileOutputStream fos = null;

				try {
					bytes = (byte[]) ois.readObject();
					fos = new FileOutputStream(file);
					fos.write(bytes);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (fos != null) {
						fos.close();

					}
				}
				// txt文本
				// byte[] bytes = new byte[1024];
				// InputStream is = socket.getInputStream();
				// FileOutputStream fos = new FileOutputStream(file);
				// BufferedOutputStream bos = new BufferedOutputStream(fos);
				// int bytesRead = is.read(bytes, 0, bytes.length);
				// bos.write(bytes, 0, bytesRead);
				// bos.close();
				socket.close();

				ClientSide.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(ClientSide.this, "Finished",
								Toast.LENGTH_LONG).show();
					}
				});

			} catch (IOException e) {

				e.printStackTrace();

				final String eMsg = "Something wrong: " + e.getMessage();
				ClientSide.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(ClientSide.this, eMsg, Toast.LENGTH_LONG)
								.show();
					}
				});

			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}