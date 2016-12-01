package com.military.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TimeUtil {
	private static final String DAY = "day";
	private static final String HOUR = "hour";
	private static final String MIN = "min";
	private static final String SEC = "sec";
	/**
	 * 单位，秒
	 */
	private static final int UNIT_SECOND = 1000;
	/**
	 * 单位，分钟
	 */
	private static final int UNIT_MINUTE = 60 * UNIT_SECOND;
	/**
	 * 单位，小时
	 */
	public static final int UNIT_HOUR = 60 * UNIT_MINUTE;

	/**
	 * 根据毫秒数返回年月
	 * 
	 * @param milliSecond
	 * @return
	 */
	public static String Millisecond2Date(long milliSecond, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = new Date(milliSecond);
		String tmp = simpleDateFormat.format(date);
		return tmp;
	}

	/**
	 * 根据毫秒数返回时间
	 * 
	 * @param milliSecond
	 * @return
	 */
	public static String Millisecond2Time(long milliSecond) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date(milliSecond);
		String tmp = simpleDateFormat.format(date);
		return tmp;
	}

	/**
	 * 根据毫秒数返回时间
	 *
	 * @param milliSecond
	 * @return
	 */
	public static String getTime(long milliSecond) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm");
		Date date = new Date(milliSecond);
		String tmp = simpleDateFormat.format(date);
		return tmp;
	}

	/**
	 * 根据毫秒数返回时间
	 *
	 * @param milliSecond
	 * @return
	 */
	public static String Millisecond2TimeByFormat(long milliSecond, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = new Date(milliSecond);
		String tmp = simpleDateFormat.format(date);
		return tmp;
	}

	public static HashMap<String, Integer> generateCountDownTime(long time) {
		HashMap<String, Integer> timeMap = new HashMap<String, Integer>();
		timeMap.put(DAY, (int) (time / 1000 / 3600 / 24));
		timeMap.put(HOUR, (int) (time / 1000 / 3600 % 24));
		timeMap.put(MIN, (int) (time / 1000 % 3600 / 60));
		timeMap.put(SEC, (int) (time / 1000 % 60));
		return timeMap;
	}

	public static String formatTime(long time) {
		HashMap<String, Integer> timeMap = generateCountDownTime(time);
		if (timeMap.get(DAY) <= 0) {
			if (timeMap.get(HOUR) <= 0) {
				return String.format("%02d:%02d", timeMap.get(MIN),timeMap.get(SEC));
			} else {
				return String.format("%02d:%02d:%02d", timeMap.get(HOUR),timeMap.get(MIN), timeMap.get(SEC));
			}

		} else {
			return String.format("%02d天%02d:%02d:%02d", timeMap.get(DAY),timeMap.get(HOUR), timeMap.get(MIN), timeMap.get(SEC));
		}
	}
}
