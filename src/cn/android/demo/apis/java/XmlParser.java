package cn.android.demo.apis.java;


import org.xmlpull.v1.XmlPullParser;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.TextView;

public class XmlParser extends Activity {
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_xml_parser);
		textView = (TextView) findViewById(R.id.tv_xml_parser_result);

		String XmlParseResult = getXmlEvent();
		textView.setText(XmlParseResult);
	}

	private String getXmlEvent() {
		String xmlResult = "";
		XmlResourceParser xmlResourceParser = getResources().getXml(

		R.layout.java_xml_parser);
		try {
			int eventType;
			do {
				xmlResourceParser.next();
				eventType = xmlResourceParser.getEventType();// 返回当前事件类型
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					xmlResult += "START_DOCUMENT \n";
					break;

				case XmlPullParser.END_DOCUMENT:
					xmlResult += "END_DOCUMENT \n";
					break;

				case XmlPullParser.START_TAG:
					xmlResult += "START_TAG:"+xmlResourceParser.getName()+" \n";
					break;
					
					
				case XmlPullParser.END_TAG:
					xmlResult += "END_TAG:"+xmlResourceParser.getName()+" \n";
					break;
				case XmlPullParser.TEXT:
					xmlResult += "TEXT:"+xmlResourceParser.getText()+" \n";
					break;

					
				default:
					break;
				}
			} while (eventType != XmlPullParser.END_DOCUMENT);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlResult;
	}
}
