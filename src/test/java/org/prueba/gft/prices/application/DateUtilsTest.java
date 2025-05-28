package org.prueba.gft.prices.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilsTest {

	private DateUtils dateUtils;

	private LocalDateTime ldt;

	@BeforeEach
	void setUp() {
		dateUtils = new DateUtils();
		ldt = dateUtils.prepareDate("2016-03-03-00.00.00");
	}

	@DisplayName("Test convert string String to LocalDateTime")
	@Test
	void prepareDate() {
		assertEquals(ldt, dateUtils.prepareDate("2016-03-03-00.00.00"));
	}

	@DisplayName("Test convert string LocalDateTime to string")
	@Test
	void transformDate() {
		assertEquals("2016-03-03-00.00.00", dateUtils.transformDate(ldt));
	}
}