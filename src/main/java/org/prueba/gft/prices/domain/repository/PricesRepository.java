package org.prueba.gft.prices.domain.repository;

import org.prueba.gft.prices.domain.model.Prices;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PricesRepository {
	Optional<Prices> findByProductIdAndBrandIdByDate(int productId, int brandId, LocalDateTime date);
}
