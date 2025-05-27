package org.prueba.gft.prices.domain.repository;

import org.prueba.gft.prices.domain.model.Prices;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesRepository {
	List<Prices> findByProductIdAndBrandIdByDate(int productId, int brandId, LocalDateTime date);
}
