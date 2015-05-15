package cn.android.demo.apis.net;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AndroidServerSocket extends Activity {

	TextView msg;
	TextView ip;
	TextView info;

	String message = "";
	ServerSocket serverSocket;

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_android_server_socket);

		msg = $id(R.id.net_server_socket_msg);
		ip = $id(R.id.net_server_socket_infoip);
		info = $id(R.id.net_server_socket_info);

		ip.setText(getIpAddress());
		Thread socketServerThread = new Thread(new SocketServerThread());
		socketServerThread.start();
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
				NetworkInterface networkInterface = enumNetworkInterfaces
						.nextElement();
				Enumeration<InetAddress> enumInetAddress = networkInterface
						.getInetAddresses();
				while (enumInetAddress.hasMoreElements()) {
					InetAddress inetAddress = enumInetAddress.nextElement();

					if (inetAddress.isSiteLocalAddress()) {
						ip += "SiteLocalAddress: "
								+ inetAddress.getHostAddress() + "\n";
					}

				}

			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ip += "Something Wrong! " + e.toString() + "\n";
		}

		return ip;
	}

	private class SocketServerReplyThread extends Thread {
		private Socket hostThreadSocket;
		int cnt;

		public SocketServerReplyThread(Socket socket, int count) {
			this.hostThreadSocket = socket;
			this.cnt = count;
		}

		@Override
		public void run() {
			OutputStream ops;
			String msgReply = "Hi,from Android.you are ### " + cnt;

			try {
				ops = hostThreadSocket.getOutputStream();
				PrintStream printStream = new PrintStream(ops);
				printStream.print(msgReply);
				printStream.close();

				message += "replayed:" + msgReply + "\n";

				AndroidServerSocket.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						msg.setText(message);
					}
				});

			} catch (IOException e) {
				e.printStackTrace();
				message += "Something wrong! " + e.toString() + "\n";
			}
			AndroidServerSocket.this.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					msg.setText(message);
				}
			});

		}

	}

	private class SocketServerThread extends Thread {
		static final int SOCKET_SERVER_PORT = 8080;
		int count = 0;

		@Override
		public void run() {
			try {
				serverSocket = new ServerSocket(SOCKET_SERVER_PORT);
				AndroidServerSocket.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						info.setText("I'm waiting here:"
								+ serverSocket.getLocalPort());
					}
				});

				while (true) {
					Socket socket = serverSocket.accept();
					count++;
					message += "#" + count + " from " + socket.getInetAddress()
							+ ":" + socket.getPort() + "\n";

					AndroidServerSocket.this.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							msg.setText(message);
						}
					});

					SocketServerReplyThread replyThread = new SocketServerReplyThread(
							socket, count);
					replyThread.run();

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
