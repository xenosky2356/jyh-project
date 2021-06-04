package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jdatepicker.JDatePicker;

public class MyCalendar {
	
	static public String getCal(JDatePicker dPic) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int h = c.get(Calendar.HOUR_OF_DAY);
		int m = c.get(Calendar.MINUTE);
		int s = c.get(Calendar.SECOND);
		String date = sdf.format(
				((Calendar) dPic.getModel().getValue()).getTime()) + " "
				+ ((h < 10) ? "0" + h : h) + ":" 
				+ ((m < 10) ? "0" + m : m) + ":" 
				+ ((s < 10) ? "0" + s : s
		);
		return date;
	}
	
	static public String Today() {
		Date Today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss");
		
		String date = sdf.format(Today);
		return date;
	}
}
