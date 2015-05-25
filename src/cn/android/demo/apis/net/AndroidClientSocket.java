package cn.android.demo.apis.net;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * socket 客户端
 * 
 * @author Elroy
 * 
 */
public class AndroidClientSocket extends Activity {
	TextView response;
	EditText address;
	EditText port;
	Button connect;
	Button clear;

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_android_client_socket);

		address = $id(R.id.net_client_socket_address);
		port = $id(R.id.net_client_socket_port);
		connect = $id(R.id.net_client_socket_connect);
		clear = $id(R.id.net_client_socket_clear);
		response = $id(R.id.net_client_socket_response);

		connect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String tMsg = "welcomeMsg";

				MyClientTask clientTask = new MyClientTask(address.getText()
						.toString(), Integer
						.parseInt(port.getText().toString()), tMsg);

				clientTask.execute();
			}
		});
		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				response.setText("");

			}
		});

	}

	public class MyClientTask extends AsyncTask<Void, Void, Void> {
		String dstAddress;
		int dstPort;
		String response = "";

		String msgToServer;

		public MyClientTask(String address, int port, String msgTo) {
			this.dstAddress = address;
			this.dstPort = port;
			this.msgToServer = msgTo;

		}

		@Override
		protected Void doInBackground(Void... params) {
			Socket socket = null;

			DataOutputStream dataOutputStream = null;
			DataInputStream dataInputStream = null;
			try {

				socket = new Socket(dstAddress, dstPort);
				// add >>>2015/05/25
				dataOutputStream = new DataOutputStream(
						socket.getOutputStream());
				dataInputStream = new DataInputStream(socket.getInputStream());

				if (msgToServer != null) {
					dataOutputStream.writeUTF(msgToServer);
				}

				response = dataInputStream.readUTF();
				// add >>>2015/05/25

				// del >>>2015/05/25
				// ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
				// byte[] buffer = new byte[1024];
				//
				// int bytesRead;
				// InputStream is = socket.getInputStream();
				// while ((bytesRead = is.read(buffer)) != -1) {
				// baos.write(buffer, 0, bytesRead);
				// response += baos.toString("UTF-8");
				//
				// }
				// del >>>2015/05/25

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
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
				if (dataOutputStream != null) {
					try {
						dataOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (dataInputStream != null) {
					try {
						dataInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			AndroidClientSocket.this.response.setText(response);
		}

	}
}
