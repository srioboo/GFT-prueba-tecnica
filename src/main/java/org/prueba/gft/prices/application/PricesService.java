package org.prueba.gft.prices.application;

import org.prueba.gft.prices.domain.Prices;
import org.prueba.gft.prices.domain.PricesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PricesService {

	private final PricesRepository pricesRepository;

	public PricesService(PricesRepository pricesRepository) {
		this.pricesRepository = pricesRepository;
	}

	public List<Prices> findAll() {
		return pricesRepository.findAll();
	}

	public List<Prices> findByProductIdAndBrandIdAndDate(int productId,
														 int brandId,
														 LocalDateTime date) {
		return pricesRepository.findByProductIdAndBrandIdAndDate(productId, brandId, date);
	}
}
