package org.prueba.gft.prices.application;

import org.prueba.gft.prices.domain.Prices;
import org.prueba.gft.prices.domain.PricesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PricesService {

	private final PricesRepository pricesRepository;

	public PricesService(PricesRepository pricesRepository) {
		this.pricesRepository = pricesRepository;
	}

	public List<Prices> findAll() {
		return pricesRepository.findAll();
	}

	public Prices findByProductIdAndBrandIdByDate(int productId,
												  int brandId,
												  LocalDateTime date) {

		List<Prices> prices = pricesRepository.findByProductIdAndBrandIdByDate(productId, brandId, date);
		Optional<Prices> opPrice = prices.stream()
			.max(Comparator.comparing(Prices::getStartDate))
			.stream().max(Comparator.comparing(Prices::getPriority));
		return opPrice.orElse(new Prices());
	}
}
