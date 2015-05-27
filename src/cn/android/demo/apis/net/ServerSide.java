package cn.android.demo.apis.net;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import cn.android.demo.apis.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 通过Socket 文件传输，在服务端启动一个线程来运行ServerSokcet，等待连接，一旦连接，启动另外一个线程来发送文件
 * 
 * @author Elroy
 * 
 */
public class ServerSide extends Activity {
	TextView infoIp;
	TextView infoPort;

	static final int SOCKET_SERVER_PORT = 8080;
	ServerSocket serverSocket;
	ServerSocketThread serverSocketThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_android_server_side);

		infoIp = (TextView) findViewById(R.id.server_side_infoip);
		infoPort = (TextView) findViewById(R.id.server_side_infoport);

		infoIp.setText(getIpAddress());

		serverSocketThread = new ServerSocketThread();
		serverSocketThread.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private String getIpAddress() {
		String ip = "";
		try {
			Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (enumNetworkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = (NetworkInterface) enumNetworkInterfaces
						.nextElement();

				Enumeration<InetAddress> enumInetAddress = networkInterface
						.getInetAddresses();

				while (enumInetAddress.hasMoreElements()) {
					InetAddress inetAddress = (InetAddress) enumInetAddress
							.nextElement();

					if (inetAddress.isSiteLocalAddress()) {
						ip += "SiteLocalAddress: "
								+ inetAddress.getHostAddress() + "\n";
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
			ip += "Something Wrong! " + e.toString() + "\n";
		}

		return ip;
	}

	class ServerSocketThread extends Thread {

		@Override
		public void run() {
			Socket socket = null;

			try {
				serverSocket = new ServerSocket(SOCKET_SERVER_PORT);
				ServerSide.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						infoPort.setText("I'm waiting here: "
								+ serverSocket.getLocalPort());
					}
				});

				while (true) {
					socket = serverSocket.accept();
					FileTxThread fileTxThread = new FileTxThread(socket);
					fileTxThread.start();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

	public class FileTxThread extends Thread {
		Socket socket;

		FileTxThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			File file = new File(Environment.getExternalStorageDirectory(),
					"MyHead.png");

			byte[] bytes = new byte[(int) file.length()];
			BufferedInputStream bis;
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
				bis.read(bytes, 0, bytes.length);
				//读取对象
				ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(bytes);
				oos.flush();
				
				//读取txt文本
				// OutputStream os = socket.getOutputStream();
				// os.write(bytes, 0, bytes.length);
				// os.flush();
				socket.close();

				final String sentMsg = "File sent to: "
						+ socket.getInetAddress();
				ServerSide.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(ServerSide.this, sentMsg,
								Toast.LENGTH_LONG).show();
					}
				});

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
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