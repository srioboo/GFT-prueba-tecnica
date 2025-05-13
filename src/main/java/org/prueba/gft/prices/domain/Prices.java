package org.prueba.gft.prices.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prices implements Serializable {

	private int brandId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private int priceList;
	private int productId;
	private int priority;
	private BigDecimal price;
	private String curr;
}
