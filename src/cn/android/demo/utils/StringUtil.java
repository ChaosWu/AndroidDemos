package cn.android.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 现在项目中有这样的需求：将“甲状腺结节 5*3 cm” 替换成 “甲状腺结节 * cm”。
	 * 
	 * 就是将字符串中的数字给替换成空白。
	 * 
	 * @param str
	 * @return
	 */
	public static String subString(String str) {
		String regex = "\\d*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while (m.find()) {
			if (!"".equals(m.group()))
				str = str.replace(m.group(), "");
		}
		return str;
	}
}
