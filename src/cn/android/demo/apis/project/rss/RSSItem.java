package cn.android.demo.apis.project.rss;

public class RSSItem {
	private String title = null;//标题
	private String description = null;//描述
	private String link = null;
	private String pubdate = null;

	RSSItem() {
	}

	void setTitle(String value) {
		title = value;
	}

	void setDescription(String value) {
		description = value;
	}

	void setLink(String value) {
		link = value;
	}

	void setPubdate(String value) {
		pubdate = value;
	}

	String getTitle() {
		return title;
	}

	String getDescription() {
		return description;
	}

	String getLink() {
		return link;
	}

	String getPubdate() {
		return pubdate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title;
	}
}
