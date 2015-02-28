package cn.android.demo.apis.project.rss;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import cn.android.demo.apis.R;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * RSS阅读器
 * 
 * @author Elroy
 * 
 */
public class AndroidRssReader extends ListActivity {

	private RSSFeed myRssFeed = null;

	TextView feedTitle;
	TextView feedDescribtion;
	TextView feedPubdate;
	TextView feedLink;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			myRssFeed = (RSSFeed) msg.obj;
			feedTitle.setText(myRssFeed.getTitle());
			feedDescribtion.setText(myRssFeed.getDescription());
			feedPubdate.setText(myRssFeed.getPubdate());
			feedLink.setText(myRssFeed.getLink());

			// ArrayAdapter<RSSItem> adapter = new ArrayAdapter<RSSItem>(
			// AndroidRssReader.this, android.R.layout.simple_list_item_1,
			// myRssFeed.getList());

			MyCustomAdapter adapter = new MyCustomAdapter(
					getApplicationContext(), R.layout.project_rss_row,
					myRssFeed.getList());
			setListAdapter(adapter);

		};
	};

	/** Called when the activity is first created. */
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project_rss_reader);
		feedTitle = (TextView) findViewById(R.id.feedtitle);
		feedDescribtion = (TextView) findViewById(R.id.feeddescribtion);
		feedPubdate = (TextView) findViewById(R.id.feedpubdate);
		feedLink = (TextView) findViewById(R.id.feedlink);
		// TODO 暴力在主线程中进行网络操作
		// StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		// .detectDiskReads().detectDiskWrites().detectNetwork()
		// .penaltyLog().build());
		// StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		// .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
		// .penaltyLog().penaltyDeath().build());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(0, 0, 0, "Reload");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case 0:
			readRss();
			break;

		default:
			break;
		}

		return true;
	}

	private void readRss() {
		new Thread(runnable).start();
	}

	private Runnable runnable = new Runnable() {

		@Override
		public void run() {
			getUrlData();
		}
	};

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, ShowDetails.class);
		Bundle bundle = new Bundle();
		bundle.putString("keyTitle", myRssFeed.getItem(position).getTitle());
		bundle.putString("keyDescription", myRssFeed.getItem(position)
				.getDescription());
		bundle.putString("keyLink", myRssFeed.getItem(position).getLink());
		bundle.putString("keyPubdate", myRssFeed.getItem(position).getPubdate());
		intent.putExtras(bundle);
		startActivity(intent);
	}

	protected void getUrlData() {
		try {
			URL rssUrl = new URL(
					"http://www.gov.hk/en/about/rss/govhkrss.data.xml");
			SAXParserFactory mySAXParserFactory = SAXParserFactory
					.newInstance();
			SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
			XMLReader myXMLReader = mySAXParser.getXMLReader();
			RSSHandler myRSSHandler = new RSSHandler();
			myXMLReader.setContentHandler(myRSSHandler);
			InputSource myInputSource = new InputSource(rssUrl.openStream());
			myXMLReader.parse(myInputSource);

			myRssFeed = myRSSHandler.getFeed();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (myRssFeed != null) {
			Message msg = new Message();
			msg.obj = myRssFeed;
			handler.sendMessage(msg);

		}
	}

	public class MyCustomAdapter extends ArrayAdapter<RSSItem> {

		public MyCustomAdapter(Context context, int textViewResourceId,
				List<RSSItem> list) {
			super(context, textViewResourceId, list);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View row = convertView;

			if (row == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.project_rss_row, parent, false);
			}

			TextView tvTitle = (TextView) row.findViewById(R.id.tv_rss_title);
			tvTitle.setText(myRssFeed.getList().get(position).getTitle());

			TextView tvPubdate = (TextView) row
					.findViewById(R.id.tv_rss_pubdate);
			tvPubdate.setText(myRssFeed.getList().get(position).getPubdate());

			if (position % 2 == 0) {
				tvTitle.setBackgroundColor(Color.BLUE);
				tvPubdate.setBackgroundColor(Color.BLUE);
			} else {
				tvTitle.setBackgroundColor(Color.GREEN);
				tvPubdate.setBackgroundColor(Color.GREEN);
			}

			return row;
		}

	}
}