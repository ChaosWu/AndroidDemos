package cn.android.demo.apis.net;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidYahooWeatherDOMActivity extends Activity {

	TextView weather;
	WebView wv;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			//weather.setText(msg.obj.toString());

			wv.loadData(msg.obj+"", "text/html", "UTF-8");
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_android_yahoo_weather_dom);
		// 测试
		// weather = (TextView) findViewById(R.id.tv_dom_weather);
		wv = (WebView) findViewById(R.id.wv_dom_weather);
		new Thread(new Runnable() {

			@Override
			public void run() {
				String weatherStr = QueryYahooWeather();
				org.w3c.dom.Document document = convertStringToDocument(weatherStr);

				String result = parseWeatherDescription(document);
				//MyWeather myWeather = parseWeather(document);

				Message msg = new Message();
				msg.obj = result;
				handler.sendMessage(msg);
			}
		}).start();
	}

	private MyWeather parseWeather(org.w3c.dom.Document srcDoc) {
		MyWeather myWeather = new MyWeather();

		// <description>Yahoo! Weather for New York, NY</description>
		// 得到文档名称为description的元素的节点列表
		myWeather.description = srcDoc.getElementsByTagName("description")
				.item(0).getTextContent();

		// <yweather:location city="New York" region="NY"
		// country="United States"/>
		Node locationNode = srcDoc.getElementsByTagName("yweather:location")
				.item(0);
		myWeather.city = locationNode.getAttributes().getNamedItem("city")
				.getNodeValue().toString();
		myWeather.region = locationNode.getAttributes().getNamedItem("region")
				.getNodeValue().toString();
		myWeather.country = locationNode.getAttributes()
				.getNamedItem("country").getNodeValue().toString();

		// <yweather:wind chill="60" direction="0" speed="0"/>
		Node windNode = srcDoc.getElementsByTagName("yweather:wind").item(0);
		myWeather.windChill = windNode.getAttributes().getNamedItem("chill")
				.getNodeValue().toString();
		myWeather.windDirection = windNode.getAttributes()
				.getNamedItem("direction").getNodeValue().toString();
		myWeather.windSpeed = windNode.getAttributes().getNamedItem("speed")
				.getNodeValue().toString();

		// <yweather:astronomy sunrise="6:52 am" sunset="7:10 pm"/>
		Node astronomyNode = srcDoc.getElementsByTagName("yweather:astronomy")
				.item(0);
		myWeather.sunrise = astronomyNode.getAttributes()
				.getNamedItem("sunrise").getNodeValue().toString();
		myWeather.sunset = astronomyNode.getAttributes().getNamedItem("sunset")
				.getNodeValue().toString();

		// <yweather:condition text="Fair" code="33" temp="60"
		// date="Fri, 23 Mar 2012 8:49 pm EDT"/>
		Node conditionNode = srcDoc.getElementsByTagName("yweather:condition")
				.item(0);
		myWeather.conditiontext = conditionNode.getAttributes()
				.getNamedItem("text").getNodeValue().toString();
		myWeather.conditiondate = conditionNode.getAttributes()
				.getNamedItem("date").getNodeValue().toString();

		return myWeather;
	}

	public String parseWeatherDescription(Document srcDoc) {

		String weatherDescription = "";

		NodeList nodeListDescription = srcDoc
				.getElementsByTagName("description");
		if (nodeListDescription.getLength() >= 0) {
			for (int i = 0; i < nodeListDescription.getLength(); i++) {
				weatherDescription += nodeListDescription.item(i)
						.getTextContent() + "<br/>";
			}
		} else {
			weatherDescription = ("No Description!");
		}

		return weatherDescription;
	}

	private org.w3c.dom.Document convertStringToDocument(String src) {
		Document dest = null;
		// 得到DOM解析器的工厂实例
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder parser;

		try {
			// 从DOM工厂中获取DOM解析器
			parser = dbFactory.newDocumentBuilder();
			// 把要解析的xml文档读入DOM解析器
			dest = parser.parse(new ByteArrayInputStream(src.getBytes()));
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
			Toast.makeText(AndroidYahooWeatherDOMActivity.this, e1.toString(),
					Toast.LENGTH_LONG).show();
		} catch (SAXException e) {
			e.printStackTrace();
			Toast.makeText(AndroidYahooWeatherDOMActivity.this, e.toString(),
					Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(AndroidYahooWeatherDOMActivity.this, e.toString(),
					Toast.LENGTH_LONG).show();
		}

		return dest;
	}

	private String QueryYahooWeather() {
		String qResult = "";
		String queryString = "http://weather.yahooapis.com/forecastrss?w=2459115";

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(queryString);

		try {
			HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();

			if (httpEntity != null) {
				InputStream inputStream = httpEntity.getContent();
				Reader in = new InputStreamReader(inputStream);
				BufferedReader bufferedreader = new BufferedReader(in);
				StringBuilder stringBuilder = new StringBuilder();

				String stringReadLine = null;

				while ((stringReadLine = bufferedreader.readLine()) != null) {
					stringBuilder.append(stringReadLine + "\n");
				}

				qResult = stringBuilder.toString();
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			Toast.makeText(AndroidYahooWeatherDOMActivity.this, e.toString(),
					Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(AndroidYahooWeatherDOMActivity.this, e.toString(),
					Toast.LENGTH_LONG).show();
		}

		return qResult;
	}

	class MyWeather {
		String description;
		String city;
		String region;
		String country;

		String windChill;
		String windDirection;
		String windSpeed;

		String sunrise;
		String sunset;

		String conditiontext;
		String conditiondate;

		@Override
		public String toString() {

			return "\n- " + description + " -\n\n" + "city: " + city + "\n"
					+ "region: " + region + "\n" + "country: " + country
					+ "\n\n"

					+ "Wind\n" + "chill: " + windChill + "\n" + "direction: "
					+ windDirection + "\n" + "speed: " + windSpeed + "\n\n"

					+ "Sunrise: " + sunrise + "\n" + "Sunset: " + sunset
					+ "\n\n"

					+ "Condition: " + conditiontext + "\n" + conditiondate
					+ "\n";
		}
	}

}
