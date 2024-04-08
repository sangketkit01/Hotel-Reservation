package Program;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateForUse extends DateForGet {
	
	private Date day2;
	private Calendar calendar2 = Calendar.getInstance();
	//constructor
	public DateForUse (Date day1, Date day2) {
		super(day1);
		this.day2 = day2;
		calendar2.setTime(day2);
	}
	
	public int getMonth2() {
		return calendar2.get(Calendar.MONTH)+1;
	}
	
	public int getDay2() {
		return calendar2.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getYear2() {
		return calendar2.get(Calendar.YEAR);
	}
	
	public int checkDate() {
		if (super.day.after(day2)) return -1;
		else if (super.getDay()== getDay2() && super.getMonth()== getMonth2() && super.getYear()== getYear2() ) return 0;
		else return 1;
	}
	
	public int getDayBetween(){
		int diffDay = 0;
		while(!super.calendar.after(calendar2)) {
			super.calendar.add(Calendar.DATE, 1);
			diffDay++;
		}
		super.calendar.setTime(super.day);
		return diffDay;
	}
	
	public String setStringforSelect() {	
		int diff = getDayBetween();
		String set = "";
		
		for(int i=1; i<diff; i++) {
			 set = set + String.format("`%02d/%02d/%d` is null and ", super.getMonth(), super.getDay(), super.getYear());
			 super.calendar.add(Calendar.DATE, 1);
		} set = set + String.format("`%02d/%02d/%d` is null", super.getMonth(), super.getDay(), super.getYear());
		super.calendar.setTime(super.day);
		return set;
	}
	
	public String setStringforUpdate(String msg) {		
		int diff = getDayBetween();
		String set = "";	
		for(int i=1; i<diff; i++) {
			 set = set + String.format("`%02d/%02d/%2d` = '%s', ", super.getMonth(), super.getDay(), super.getYear(),msg);
			 super.calendar.add(Calendar.DATE, 1);
		} set = set + String.format("`%02d/%02d/%d` = '%s'", super.getMonth(), super.getDay(), super.getYear(),msg);
		super.calendar.setTime(super.day);
		return set;
	}
	
	public static String DateConvert(Date inputDate) { 
	        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
	        try {
	            String outputDate = outputFormat.format(inputDate);
	            return outputDate;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
			
}
