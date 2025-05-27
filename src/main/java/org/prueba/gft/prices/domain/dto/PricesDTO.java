package org.prueba.gft.prices.domain.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PricesDTO {

	private int brandId;
	private String startDate;
	private String endDate;
	private int priceList;
	private int productId;
	private int priority;
	private BigDecimal price;
	private String curr;
}
