package nwt.orders;

import java.time.DateTimeException;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateValidator {
public static void validateDate(Date date) {
	if(date.after(new Date())) throw new DateTimeException("Given date is greater than current date");
}
public static void compareDates(Date date1, Date date2) {
	if(date1.after(date2)) throw new DateTimeException("First date is greater than second");
}
	
}
