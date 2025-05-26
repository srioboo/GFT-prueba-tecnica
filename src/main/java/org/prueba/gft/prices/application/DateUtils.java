package org.prueba.gft.prices.application;

import org.prueba.gft.prices.domain.DateUtilsHelper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class DateUtils implements DateUtilsHelper {

	private static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.of("ES", "ES"));

	public LocalDateTime prepareDate(String date) {
		return LocalDateTime.parse(date, FORMATTER);
	}

	public String transformDate(LocalDateTime date) {
		return date.format(FORMATTER);
	}

}
