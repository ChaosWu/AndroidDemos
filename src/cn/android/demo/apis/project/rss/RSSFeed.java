package cn.android.demo.apis.project.rss;

import java.util.List;
import java.util.Vector;

public class RSSFeed {
	private String title = null;//标题
	private String description = null;//详情
	private String link = null;//链接
	private String pubdate = null;//出版日期
	private List<RSSItem> itemList;

	// 测试反射用
	public RSSFeed(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitleAndDes() {
		return "标题：" + title + "\n" + "详情：" + description;

	}

	public RSSFeed() {
		itemList = new Vector<RSSItem>(0);
	}

	public void addItem(RSSItem item) {
		itemList.add(item);
	}

	public RSSItem getItem(int location) {
		return itemList.get(location);
	}

	public List<RSSItem> getList() {
		return itemList;
	}

	public void setTitle(String value) {
		title = value;
	}

	public void setDescription(String value) {
		description = value;
	}

	public void setLink(String value) {
		link = value;
	}

	public void setPubdate(String value) {
		pubdate = value;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getLink() {
		return link;
	}

	public String getPubdate() {
		return pubdate;
	}

}