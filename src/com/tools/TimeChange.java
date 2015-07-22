package com.tools;

import java.text.SimpleDateFormat;

public class TimeChange {
	public static String tc(String timechuo) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Long time = Long.parseLong(timechuo);
		String d = format.format(time * 1000);
		return d;
	}
}
