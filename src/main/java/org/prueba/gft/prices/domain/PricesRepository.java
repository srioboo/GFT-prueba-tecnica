package org.prueba.gft.prices.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesRepository {

	List<Prices> findAll();

	List<Prices> findByProductIdAndBrandIdAndStartDate(int productId, int brandId, LocalDateTime date);

	List<Prices> findByProductIdAndBrandIdAndStartDateAndEndDate(int productId, int brandId,
																 LocalDateTime startDate, LocalDateTime endDate);

	List<Prices> findByProductIdAndBrandId(int productId, int brandId);
}
