package org.prueba.gft.prices.domain.repository;

import org.prueba.gft.prices.domain.model.PriceNotFoundException;
import org.prueba.gft.prices.domain.model.Prices;

import java.time.LocalDateTime;

public interface PricesRepository {
	Prices findByProductIdAndBrandIdByDate(int productId, int brandId, LocalDateTime date) throws PriceNotFoundException;
}
