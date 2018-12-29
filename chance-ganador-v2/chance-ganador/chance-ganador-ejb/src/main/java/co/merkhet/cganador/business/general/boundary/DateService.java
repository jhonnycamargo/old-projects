package co.merkhet.cganador.business.general.boundary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import co.merkhet.cganador.business.general.entity.Pais;

public class DateService {

	private static final String DATE_FORMAT = "dd-MM-yyyy";

	public DateService() {
		super();
	}

	public Date currentDateByCountry(Pais pais) throws ParseException {
		Calendar calendar = new GregorianCalendar();
		String[] timeZoneIds = com.ibm.icu.util.TimeZone.getAvailableIDs(pais
				.getCodigo());
		TimeZone timeZone = TimeZone.getDefault();
		if (timeZoneIds != null && timeZoneIds.length > 0) {
			TimeZone.getTimeZone(timeZoneIds[0]);
		}
		calendar.setTimeZone(timeZone);
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		dateFormat.setTimeZone(timeZone);

		return dateFormat.parse(dateFormat.format(calendar.getTime()));
	}
}
