package cn.android.demo.apis.ui.widget;

import java.util.ArrayList;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * TODO 没有效果，回头查看 -->联系人源码
 * 
 * @author Elroy
 * 
 */
public class ListViewHeaderActivity extends Activity {
	private int mItemHeight = 55;
	private int mSecHeight = 25;
	private float density;

	// name_date
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		density = getResources().getDisplayMetrics().density;
		mItemHeight = (int) (density * mItemHeight);
		mSecHeight = (int) (density * mSecHeight);

		PinnedHeaderListView mListView = new PinnedHeaderListView(this);

		mListView.setAdapter(new ListViewAdapter(filledData()));

		mListView.setPinnedHeaderView(getHeaderView());
		mListView.setBackgroundColor(Color.argb(255, 20, 20, 20));
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListViewAdapter adapter = ((ListViewAdapter) parent
						.getAdapter());
				Contact data = (Contact) adapter.getItem(position);
				// Toast.makeText(ListViewHeaderActivity.this, data.toString(),
				// Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(ListViewHeaderActivity.this,
						ListViewCommentsActivity.class);
				int[] startingLocation = new int[2];
				view.getLocationOnScreen(startingLocation);
				intent.putExtra(
						ListViewCommentsActivity.ARG_DRAWING_START_LOCATION,
						startingLocation[1]);
				intent.putExtra(ListViewCommentsActivity.NAME, data.toString());
				startActivity(intent);
				overridePendingTransition(0, 0);
			}
		});

		setContentView(mListView);
	}

	/**
	 * 为ListView填充数据
	 * 
	 * @param date
	 * @return
	 */
	public ArrayList<Contact> filledData() {
		String[] date = getResources().getStringArray(R.array.name_date);

		ArrayList<Contact> mSortList = new ArrayList<Contact>();

		for (int i = 0; i < date.length; i++) {
			Contact contact = new Contact(i, date[i]);
			mSortList.add(contact);
		}
		return mSortList;

	}

	private View getHeaderView() {
		TextView itemView = new TextView(ListViewHeaderActivity.this);
		itemView.setLayoutParams(new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, mSecHeight));
		itemView.setGravity(Gravity.CENTER_VERTICAL);
		itemView.setBackgroundColor(Color.WHITE);
		itemView.setTextSize(20);
		itemView.setTextColor(Color.GRAY);
		itemView.setBackgroundResource(R.drawable.ios_dialog_bg);
		itemView.setPadding(10, 0, 0, itemView.getPaddingBottom());

		return itemView;
	}

	private class ListViewAdapter extends BaseAdapter implements
			PinnedHeaderAdapter {
		private ArrayList<Contact> mDatas;
		private static final int TYPE_CATEGORY_ITEM = 0;
		private static final int TYPE_ITEM = 1;

		public ListViewAdapter(ArrayList<Contact> datas) {
			mDatas = datas;
		}

		@Override
		public boolean areAllItemsEnabled() {
			return false;
		}

		@Override
		public boolean isEnabled(int position) {
			// 异常情况处理
			if (null == mDatas || position < 0 || position > getCount()) {
				return true;
			}

			Contact item = mDatas.get(position);
			if (item.isSection) {
				return false;
			}

			return true;
		}

		@Override
		public int getCount() {
			return mDatas.size();
		}

		@Override
		public int getItemViewType(int position) {
			// 异常情况处理
			if (null == mDatas || position < 0 || position > getCount()) {
				return TYPE_ITEM;
			}

			Contact item = mDatas.get(position);
			if (item.isSection) {
				return TYPE_CATEGORY_ITEM;
			}

			return TYPE_ITEM;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		@Override
		public Object getItem(int position) {
			return (position >= 0 && position < mDatas.size()) ? mDatas
					.get(position) : 0;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			int itemViewType = getItemViewType(position);
			Contact data = (Contact) getItem(position);
			TextView itemView;

			switch (itemViewType) {
			case TYPE_ITEM:
				if (null == convertView) {
					itemView = new TextView(ListViewHeaderActivity.this);
					itemView.setLayoutParams(new AbsListView.LayoutParams(
							ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight));
					itemView.setTextSize(16);
					itemView.setPadding(10, 0, 0, 0);
					itemView.setGravity(Gravity.CENTER_VERTICAL);
					// itemView.setBackgroundColor(Color.argb(255, 20, 20, 20));
					convertView = itemView;
				}

				itemView = (TextView) convertView;
				itemView.setText(data.toString());
				break;

			case TYPE_CATEGORY_ITEM:
				if (null == convertView) {
					convertView = getHeaderView();
				}
				itemView = (TextView) convertView;
				itemView.setText(data.toString());
				break;
			}

			return convertView;
		}

		@Override
		public int getPinnedHeaderState(int position) {
			if (position < 0) {
				return PINNED_HEADER_GONE;
			}

			Contact item = (Contact) getItem(position);
			Contact itemNext = (Contact) getItem(position + 1);
			boolean isSection = item.isSection;
			boolean isNextSection = (null != itemNext) ? itemNext.isSection
					: false;
			if (!isSection && isNextSection) {
				return PINNED_HEADER_PUSHED_UP;
			}

			return PINNED_HEADER_VISIBLE;
		}

		@Override
		public void configurePinnedHeader(View header, int position, int alpha) {
			Contact item = (Contact) getItem(position);
			if (null != item) {
				if (header instanceof TextView) {
					((TextView) header).setText(item.sectionStr);
				}
			}
		}

	}
}

// 数据结构Contact
class Contact {
	int id;
	String name;
	String pinyin;
	String sortLetter = "#";
	String sectionStr;
	String phoneNumber;
	boolean isSection;
	static CharacterParser sParser = CharacterParser.getInstance();

	Contact() {

	}

	Contact(int id, String name) {
		this.id = id;
		this.name = name;
		this.pinyin = sParser.getSpelling(name);
		if (!TextUtils.isEmpty(pinyin)) {
			String sortString = this.pinyin.substring(0, 1).toUpperCase();
			if (sortString.matches("[A-Z]")) {
				this.sortLetter = sortString.toUpperCase();
			} else {
				this.sortLetter = "#";
			}
		}
	}

	@Override
	public String toString() {
		if (isSection) {
			return name;
		} else {
			// return name + " (" + sortLetter + ", " + pinyin + ")";
			return name + " (" + phoneNumber + ")";
		}
	}
}

/**
 * Adapter interface. The list adapter must implement this interface.
 */
interface PinnedHeaderAdapter {

	/**
	 * Pinned header state: don't show the header.
	 */
	public static final int PINNED_HEADER_GONE = 0;

	/**
	 * Pinned header state: show the header at the top of the list.
	 */
	public static final int PINNED_HEADER_VISIBLE = 1;

	/**
	 * Pinned header state: show the header. If the header extends beyond the
	 * bottom of the first shown element, push it up and clip.
	 */
	public static final int PINNED_HEADER_PUSHED_UP = 2;

	/**
	 * Computes the desired state of the pinned header for the given position of
	 * the first visible list item. Allowed return values are
	 * {@link #PINNED_HEADER_GONE}, {@link #PINNED_HEADER_VISIBLE} or
	 * {@link #PINNED_HEADER_PUSHED_UP}.
	 */
	int getPinnedHeaderState(int position);

	/**
	 * Configures the pinned header view to match the first visible list item.
	 * 
	 * @param header
	 *            pinned header view.
	 * @param position
	 *            position of the first visible list item.
	 * @param alpha
	 *            fading of the header view, between 0 and 255.
	 */
	void configurePinnedHeader(View header, int position, int alpha);
}

/**
 * Java汉字转换为拼音
 * 
 */
class CharacterParser {
	private static int[] pyvalue = new int[] { -20319, -20317, -20304, -20295,
			-20292, -20283, -20265, -20257, -20242, -20230, -20051, -20036,
			-20032, -20026, -20002, -19990, -19986, -19982, -19976, -19805,
			-19784, -19775, -19774, -19763, -19756, -19751, -19746, -19741,
			-19739, -19728, -19725, -19715, -19540, -19531, -19525, -19515,
			-19500, -19484, -19479, -19467, -19289, -19288, -19281, -19275,
			-19270, -19263, -19261, -19249, -19243, -19242, -19238, -19235,
			-19227, -19224, -19218, -19212, -19038, -19023, -19018, -19006,
			-19003, -18996, -18977, -18961, -18952, -18783, -18774, -18773,
			-18763, -18756, -18741, -18735, -18731, -18722, -18710, -18697,
			-18696, -18526, -18518, -18501, -18490, -18478, -18463, -18448,
			-18447, -18446, -18239, -18237, -18231, -18220, -18211, -18201,
			-18184, -18183, -18181, -18012, -17997, -17988, -17970, -17964,
			-17961, -17950, -17947, -17931, -17928, -17922, -17759, -17752,
			-17733, -17730, -17721, -17703, -17701, -17697, -17692, -17683,
			-17676, -17496, -17487, -17482, -17468, -17454, -17433, -17427,
			-17417, -17202, -17185, -16983, -16970, -16942, -16915, -16733,
			-16708, -16706, -16689, -16664, -16657, -16647, -16474, -16470,
			-16465, -16459, -16452, -16448, -16433, -16429, -16427, -16423,
			-16419, -16412, -16407, -16403, -16401, -16393, -16220, -16216,
			-16212, -16205, -16202, -16187, -16180, -16171, -16169, -16158,
			-16155, -15959, -15958, -15944, -15933, -15920, -15915, -15903,
			-15889, -15878, -15707, -15701, -15681, -15667, -15661, -15659,
			-15652, -15640, -15631, -15625, -15454, -15448, -15436, -15435,
			-15419, -15416, -15408, -15394, -15385, -15377, -15375, -15369,
			-15363, -15362, -15183, -15180, -15165, -15158, -15153, -15150,
			-15149, -15144, -15143, -15141, -15140, -15139, -15128, -15121,
			-15119, -15117, -15110, -15109, -14941, -14937, -14933, -14930,
			-14929, -14928, -14926, -14922, -14921, -14914, -14908, -14902,
			-14894, -14889, -14882, -14873, -14871, -14857, -14678, -14674,
			-14670, -14668, -14663, -14654, -14645, -14630, -14594, -14429,
			-14407, -14399, -14384, -14379, -14368, -14355, -14353, -14345,
			-14170, -14159, -14151, -14149, -14145, -14140, -14137, -14135,
			-14125, -14123, -14122, -14112, -14109, -14099, -14097, -14094,
			-14092, -14090, -14087, -14083, -13917, -13914, -13910, -13907,
			-13906, -13905, -13896, -13894, -13878, -13870, -13859, -13847,
			-13831, -13658, -13611, -13601, -13406, -13404, -13400, -13398,
			-13395, -13391, -13387, -13383, -13367, -13359, -13356, -13343,
			-13340, -13329, -13326, -13318, -13147, -13138, -13120, -13107,
			-13096, -13095, -13091, -13076, -13068, -13063, -13060, -12888,
			-12875, -12871, -12860, -12858, -12852, -12849, -12838, -12831,
			-12829, -12812, -12802, -12607, -12597, -12594, -12585, -12556,
			-12359, -12346, -12320, -12300, -12120, -12099, -12089, -12074,
			-12067, -12058, -12039, -11867, -11861, -11847, -11831, -11798,
			-11781, -11604, -11589, -11536, -11358, -11340, -11339, -11324,
			-11303, -11097, -11077, -11067, -11055, -11052, -11045, -11041,
			-11038, -11024, -11020, -11019, -11018, -11014, -10838, -10832,
			-10815, -10800, -10790, -10780, -10764, -10587, -10544, -10533,
			-10519, -10331, -10329, -10328, -10322, -10315, -10309, -10307,
			-10296, -10281, -10274, -10270, -10262, -10260, -10256, -10254 };
	public static String[] pystr = new String[] { "a", "ai", "an", "ang", "ao",
			"ba", "bai", "ban", "bang", "bao", "bei", "ben", "beng", "bi",
			"bian", "biao", "bie", "bin", "bing", "bo", "bu", "ca", "cai",
			"can", "cang", "cao", "ce", "ceng", "cha", "chai", "chan", "chang",
			"chao", "che", "chen", "cheng", "chi", "chong", "chou", "chu",
			"chuai", "chuan", "chuang", "chui", "chun", "chuo", "ci", "cong",
			"cou", "cu", "cuan", "cui", "cun", "cuo", "da", "dai", "dan",
			"dang", "dao", "de", "deng", "di", "dian", "diao", "die", "ding",
			"diu", "dong", "dou", "du", "duan", "dui", "dun", "duo", "e", "en",
			"er", "fa", "fan", "fang", "fei", "fen", "feng", "fo", "fou", "fu",
			"ga", "gai", "gan", "gang", "gao", "ge", "gei", "gen", "geng",
			"gong", "gou", "gu", "gua", "guai", "guan", "guang", "gui", "gun",
			"guo", "ha", "hai", "han", "hang", "hao", "he", "hei", "hen",
			"heng", "hong", "hou", "hu", "hua", "huai", "huan", "huang", "hui",
			"hun", "huo", "ji", "jia", "jian", "jiang", "jiao", "jie", "jin",
			"jing", "jiong", "jiu", "ju", "juan", "jue", "jun", "ka", "kai",
			"kan", "kang", "kao", "ke", "ken", "keng", "kong", "kou", "ku",
			"kua", "kuai", "kuan", "kuang", "kui", "kun", "kuo", "la", "lai",
			"lan", "lang", "lao", "le", "lei", "leng", "li", "lia", "lian",
			"liang", "liao", "lie", "lin", "ling", "liu", "long", "lou", "lu",
			"lv", "luan", "lue", "lun", "luo", "ma", "mai", "man", "mang",
			"mao", "me", "mei", "men", "meng", "mi", "mian", "miao", "mie",
			"min", "ming", "miu", "mo", "mou", "mu", "na", "nai", "nan",
			"nang", "nao", "ne", "nei", "nen", "neng", "ni", "nian", "niang",
			"niao", "nie", "nin", "ning", "niu", "nong", "nu", "nv", "nuan",
			"nue", "nuo", "o", "ou", "pa", "pai", "pan", "pang", "pao", "pei",
			"pen", "peng", "pi", "pian", "piao", "pie", "pin", "ping", "po",
			"pu", "qi", "qia", "qian", "qiang", "qiao", "qie", "qin", "qing",
			"qiong", "qiu", "qu", "quan", "que", "qun", "ran", "rang", "rao",
			"re", "ren", "reng", "ri", "rong", "rou", "ru", "ruan", "rui",
			"run", "ruo", "sa", "sai", "san", "sang", "sao", "se", "sen",
			"seng", "sha", "shai", "shan", "shang", "shao", "she", "shen",
			"sheng", "shi", "shou", "shu", "shua", "shuai", "shuan", "shuang",
			"shui", "shun", "shuo", "si", "song", "sou", "su", "suan", "sui",
			"sun", "suo", "ta", "tai", "tan", "tang", "tao", "te", "teng",
			"ti", "tian", "tiao", "tie", "ting", "tong", "tou", "tu", "tuan",
			"tui", "tun", "tuo", "wa", "wai", "wan", "wang", "wei", "wen",
			"weng", "wo", "wu", "xi", "xia", "xian", "xiang", "xiao", "xie",
			"xin", "xing", "xiong", "xiu", "xu", "xuan", "xue", "xun", "ya",
			"yan", "yang", "yao", "ye", "yi", "yin", "ying", "yo", "yong",
			"you", "yu", "yuan", "yue", "yun", "za", "zai", "zan", "zang",
			"zao", "ze", "zei", "zen", "zeng", "zha", "zhai", "zhan", "zhang",
			"zhao", "zhe", "zhen", "zheng", "zhi", "zhong", "zhou", "zhu",
			"zhua", "zhuai", "zhuan", "zhuang", "zhui", "zhun", "zhuo", "zi",
			"zong", "zou", "zu", "zuan", "zui", "zun", "zuo" };
	private StringBuilder buffer;
	private String resource;
	private static CharacterParser characterParser = new CharacterParser();

	public static CharacterParser getInstance() {
		return characterParser;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	/** * 汉字转成ASCII码 * * @param chs * @return */
	private int getChsAscii(String chs) {
		int asc = 0;
		try {
			byte[] bytes = chs.getBytes("gb2312");
			if (bytes == null || bytes.length > 2 || bytes.length <= 0) {
				throw new RuntimeException("illegal resource string");
			}
			if (bytes.length == 1) {
				asc = bytes[0];
			}
			if (bytes.length == 2) {
				int hightByte = 256 + bytes[0];
				int lowByte = 256 + bytes[1];
				asc = (256 * hightByte + lowByte) - 256 * 256;
			}
		} catch (Exception e) {
			System.out
					.println("ERROR:ChineseSpelling.class-getChsAscii(String chs)"
							+ e);
		}
		return asc;
	}

	/** * 单字解析 * * @param str * @return */
	public String convert(String str) {
		String result = null;
		int ascii = getChsAscii(str);
		if (ascii > 0 && ascii < 160) {
			result = String.valueOf((char) ascii);
		} else {
			for (int i = (pyvalue.length - 1); i >= 0; i--) {
				if (pyvalue[i] <= ascii) {
					result = pystr[i];
					break;
				}
			}
		}
		return result;
	}

	/** * 词组解析 * * @param chs * @return */
	public String getSelling(String chs) {
		String key, value;
		buffer = new StringBuilder();
		for (int i = 0; i < chs.length(); i++) {
			key = chs.substring(i, i + 1);
			if (key.getBytes().length >= 2) {
				value = (String) convert(key);
				if (value == null) {
					value = "unknown";
				}
			} else {
				value = key;
			}
			buffer.append(value);
		}
		return buffer.toString();
	}

	public String getSpelling() {
		return this.getSelling(this.getResource());
	}

	public String getSpelling(String name) {
		return getSelling(name);
	}

}
