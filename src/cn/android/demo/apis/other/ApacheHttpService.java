package cn.android.demo.apis.other;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerRegistry;
import org.apache.http.protocol.HttpService;
import org.apache.http.protocol.ResponseConnControl;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.protocol.ResponseServer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

/**
 * 使用HttpService 搭建简单web服务器
 * 
 * 基于经典阻塞I/O模型 、HTTP协议处理
 * 
 * http://<ip address>:8080			游览首页
 * 
 * http://<ip address>:8080/image   下载图片
 * @author Elroy
 * 
 */
public class ApacheHttpService extends Activity {

	TextView infoIP;
	HttpServiceThread httpServiceThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		infoIP = new TextView(this);
		setContentView(infoIP);

		infoIP.setText(getIpAddress() + " : "
				+ HttpServiceThread.HTTP_SERVER_PORT + "\n");

		httpServiceThread = new HttpServiceThread();
		httpServiceThread.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		httpServiceThread.stopServer();
	}

	private String getIpAddress() {
		String ip = "";

		try {
			Enumeration<NetworkInterface> enumNetworkInterface = NetworkInterface
					.getNetworkInterfaces();
			while (enumNetworkInterface.hasMoreElements()) {
				NetworkInterface networkInterface = (NetworkInterface) enumNetworkInterface
						.nextElement();

				Enumeration<InetAddress> enumInetAddress = networkInterface
						.getInetAddresses();
				while (enumInetAddress.hasMoreElements()) {
					InetAddress inetAddress = (InetAddress) enumInetAddress
							.nextElement();
					if (inetAddress.isSiteLocalAddress()) {
						ip += "SiteLocalAddress: "
								+ inetAddress.getHostAddress() + " \n";
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			ip += "Something Wrong! " + e.toString() + "\n";
		}

		return ip;
	}

	class HttpServiceThread extends Thread {

		public static final int HTTP_SERVER_PORT = 8080;

		Socket socket;
		ServerSocket serverSocket;
		HttpService httpService;
		BasicHttpContext basicHttpContext;

		boolean isRunning = false;

		public HttpServiceThread() {
			isRunning = true;
			startHttpService();
		}

		@Override
		public void run() {
			super.run();

			try {
				serverSocket = new ServerSocket(HTTP_SERVER_PORT);
				serverSocket.setReuseAddress(true);

				while (isRunning) {
					socket = serverSocket.accept();

					DefaultHttpServerConnection defaultHttpServerConnection = new DefaultHttpServerConnection();

					defaultHttpServerConnection.bind(socket,
							new BasicHttpParams());
					httpService.handleRequest(defaultHttpServerConnection,
							basicHttpContext);
					defaultHttpServerConnection.shutdown();

				}
				serverSocket.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private void startHttpService() {
			BasicHttpProcessor basicHttpProcessor = new BasicHttpProcessor();
			basicHttpContext = new BasicHttpContext();

			basicHttpProcessor.addInterceptor(new ResponseDate());
			basicHttpProcessor.addInterceptor(new ResponseServer());
			basicHttpProcessor.addInterceptor(new ResponseContent());
			basicHttpProcessor.addInterceptor(new ResponseConnControl());

			httpService = new HttpService(basicHttpProcessor,
					new DefaultConnectionReuseStrategy(),
					new DefaultHttpResponseFactory());

			HttpRequestHandlerRegistry registry = new HttpRequestHandlerRegistry();
			//
			registry.register("/", new HomeCommandHandler());
			// 图片
			registry.register("/image", new ImageCommandHandler());
			httpService.setHandlerResolver(registry);
		}

		public void stopServer() {
			isRunning = false;
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	class HomeCommandHandler implements HttpRequestHandler {

		@Override
		public void handle(HttpRequest request, HttpResponse response,
				HttpContext context) throws HttpException, IOException {

			HttpEntity httpEntity = new EntityTemplate(new ContentProducer() {

				@Override
				public void writeTo(OutputStream outstream) throws IOException {
					OutputStreamWriter osw = new OutputStreamWriter(outstream,
							"UTF-8");
					String response = "<html><head></head><body><h1>Hello HttpService, from Android-er<h1></body></html>";

					osw.write(response);
					osw.flush();

				}
			});
			response.setHeader("Content-Type", "text/html");
			response.setEntity(httpEntity);
		}

	}

	class ImageCommandHandler implements HttpRequestHandler {

		@Override
		public void handle(HttpRequest request, HttpResponse response,
				HttpContext context) throws HttpException, IOException {

			File file = new File(Environment.getExternalStorageDirectory(),
					"MyHead.png");

			FileEntity fileEntity = new FileEntity(file, "image/*");
//			response.setHeader("Content-Type", "application/force-download");
//			response.setHeader("Content-Disposition",
//					"attachment; filename=image.png");
			response.setHeader("Content-Type", "image/*");
			response.setEntity(fileEntity);
		}

	}

}
