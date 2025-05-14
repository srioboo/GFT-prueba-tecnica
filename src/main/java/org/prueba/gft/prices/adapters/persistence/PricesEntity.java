package org.prueba.gft.prices.adapters.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@Data
class PricesEntity {

	@Id
	private int priceList;
	private int productId;
	private int brandId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private int priority;

	@Column(precision = 10, scale = 2)
	private BigDecimal price;
	private String curr;
}
