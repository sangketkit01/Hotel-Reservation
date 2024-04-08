package Program;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public abstract class DateForGet {
	
	protected Date day ;	
	protected Calendar calendar = Calendar.getInstance();
	
	public DateForGet(Date day) {
		this.day = day;	
		calendar.setTime(day);
	}
	
	public int getMonth() {	
		return calendar.get(Calendar.MONTH)+1;
	}
	
	public int getDay() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}
		
	public abstract int checkDate();		
	public abstract int getDayBetween();
}
