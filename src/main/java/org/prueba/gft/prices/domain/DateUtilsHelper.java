package org.prueba.gft.prices.domain;

import java.time.LocalDateTime;

public interface DateUtilsHelper {
	LocalDateTime prepareDate(String date);

	String transformDate(LocalDateTime date);
}
