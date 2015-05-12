package cn.android.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	/**
	 * 根据开始日期 ，需要的工作日天数 ，计算工作截止日期，并返回截止日期
	 * 
	 * @param startDate
	 *            开始日期
	 * @param workDay
	 *            工作日天数(周一到周五)
	 * @return Date类型
	 * @createTime 2014-2-14
	 * @author Sunqinbo
	 */
	public static Date getWorkDay(Date startDate, int workDay) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(startDate);
		for (int i = 0; i < workDay; i++) {
			c1.set(Calendar.DATE, c1.get(Calendar.DATE) + 1);
			if (Calendar.SATURDAY == c1.get(Calendar.SATURDAY)
					|| Calendar.SUNDAY == c1.get(Calendar.SUNDAY)) {
				workDay = workDay + 1;
				c1.set(Calendar.DATE, c1.get(Calendar.DATE) + 1);
				continue;
			}
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(c1.getTime()) + " "
				+ getWeekOfDate(c1.getTime()));
		return c1.getTime();
	}

	/**
	 * 根据日期，获取星期几
	 * 
	 * @param dt
	 * @return String类型
	 * @createTime 2014-2-14
	 * @author Sunqinbo
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

}
