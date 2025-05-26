package org.prueba.gft.prices.domain;

import java.time.LocalDateTime;

public interface DateUtilsHelper {
	public LocalDateTime prepareDate(String date);

	public String transformDate(LocalDateTime date);
}
