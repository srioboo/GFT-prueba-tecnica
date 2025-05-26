package org.prueba.gft.prices.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

	private static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.of("ES", "ES"));

	public static LocalDateTime prepareDate(String date) {
		return LocalDateTime.parse(date, FORMATTER);
	}

	public static String transformDate(LocalDateTime date) {
		return date.format(FORMATTER);
	}

}
