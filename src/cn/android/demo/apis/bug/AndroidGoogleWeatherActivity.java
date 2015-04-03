package cn.android.demo.apis.bug;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

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
import android.widget.TextView;
import android.widget.Toast;
//报错，未解决
public class AndroidGoogleWeatherActivity extends Activity {
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			tvCurrentConditions.setText((String) msg.obj);
		}

	};

	class CurrentConditions {
		String condition;
		String temp_f;
		String temp_c;
		String humidity;
		String icon;
		String wind_condition;
	}

	class ForecastConditions {
		String day_of_week;
		String low;
		String high;
		String icon;
		String condition;
	}

	CurrentConditions currentConditions;
	List<ForecastConditions> forecastConditionsList;

	TextView tvCurrentConditions;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_android_google_weather_dom);
		tvCurrentConditions = (TextView) findViewById(R.id.tv_google_currentconditions);
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				String weatherString = QueryGoogleWeather();
				Document weatherDoc = convertStringToDocument(weatherString);
				parseGoogleWeather(weatherDoc);

				// Display Result
				String s = "\n" + currentConditions.condition + "\n"
						+ currentConditions.temp_f + "\n"
						+ currentConditions.temp_c + "\n"
						+ currentConditions.humidity + "\n"
						+ currentConditions.icon + "\n"
						+ currentConditions.wind_condition + "\n";

				for (int k = 0; k < 4; k++) {
					s += "\n" + forecastConditionsList.get(k).condition + "\n"
							+ forecastConditionsList.get(k).day_of_week + "\n"
							+ forecastConditionsList.get(k).low + "\n"
							+ forecastConditionsList.get(k).high + "\n"
							+ forecastConditionsList.get(k).icon + "\n";
				}
				Message msg = new Message();
				msg.obj = s;
				handler.sendMessage(msg);
			}
		}).start();
	}

	private void parseGoogleWeather(Document srcDoc) {

		currentConditions = new CurrentConditions();

		// -- Get current_conditions
		NodeList current_conditions = srcDoc
				.getElementsByTagName("current_conditions");
		NodeList currentChilds = current_conditions.item(0).getChildNodes();

		for (int i = 0; i < currentChilds.getLength(); i++) {
			Node n = currentChilds.item(i);

			String nName = n.getNodeName();
			String nValue = n.getAttributes().getNamedItem("data")
					.getNodeValue().toString();
			if (nName.equalsIgnoreCase("condition")) {
				currentConditions.condition = nValue;
			} else if ((nName.equalsIgnoreCase("temp_f"))) {
				currentConditions.temp_f = nValue;
			} else if ((nName.equalsIgnoreCase("temp_c"))) {
				currentConditions.temp_c = nValue;
			} else if ((nName.equalsIgnoreCase("humidity"))) {
				currentConditions.humidity = nValue;
			} else if ((nName.equalsIgnoreCase("icon"))) {
				currentConditions.icon = nValue;
			} else if ((nName.equalsIgnoreCase("wind_condition"))) {
				currentConditions.wind_condition = nValue;
			}
		}

		// -- Get forecast_conditions
		NodeList forecast_conditions = srcDoc
				.getElementsByTagName("forecast_conditions");
		int forecast_conditions_length = forecast_conditions.getLength();

		forecastConditionsList = new ArrayList<ForecastConditions>();

		for (int j = 0; j < forecast_conditions_length; j++) {

			ForecastConditions tmpForecastConditions = new ForecastConditions();

			NodeList forecasrChilds = forecast_conditions.item(j)
					.getChildNodes();

			for (int i = 0; i < forecasrChilds.getLength(); i++) {

				Node n = forecasrChilds.item(i);

				String nName = n.getNodeName();
				String nValue = n.getAttributes().getNamedItem("data")
						.getNodeValue().toString();

				if (nName.equalsIgnoreCase("condition")) {
					tmpForecastConditions.condition = nValue;
				} else if ((nName.equalsIgnoreCase("day_of_week"))) {
					tmpForecastConditions.day_of_week = nValue;
				} else if ((nName.equalsIgnoreCase("low"))) {
					tmpForecastConditions.low = nValue;
				} else if ((nName.equalsIgnoreCase("high"))) {
					tmpForecastConditions.high = nValue;
				} else if ((nName.equalsIgnoreCase("icon"))) {
					tmpForecastConditions.icon = nValue;
				}

			}

			forecastConditionsList.add(tmpForecastConditions);

		}

	}

	private Document convertStringToDocument(String src) {
		Document dest = null;

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser;

		try {
			parser = dbFactory.newDocumentBuilder();
			dest = parser.parse(new ByteArrayInputStream(src.getBytes()));
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
			Toast.makeText(AndroidGoogleWeatherActivity.this, e1.toString(),
					Toast.LENGTH_LONG).show();
		} catch (SAXException e) {
			e.printStackTrace();
			Toast.makeText(AndroidGoogleWeatherActivity.this, e.toString(),
					Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(AndroidGoogleWeatherActivity.this, e.toString(),
					Toast.LENGTH_LONG).show();
		}

		return dest;
	}

	private String QueryGoogleWeather() {
		String qResult = "";
		String queryString = "http://www.google.com/ig/api?hl=en&weather=new%20york";

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
			Toast.makeText(AndroidGoogleWeatherActivity.this, e.toString(),
					Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(AndroidGoogleWeatherActivity.this, e.toString(),
					Toast.LENGTH_LONG).show();
		}

		return qResult;
	}
}