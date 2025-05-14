package org.prueba.gft.prices.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prices { // implements Serializable {

	private int brandId;
	private String startDate;
	private String endDate;
	private int priceList;
	private int productId;
	private int priority;
	private BigDecimal price;
	private String curr;
}
