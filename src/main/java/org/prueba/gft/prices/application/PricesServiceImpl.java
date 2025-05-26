package org.prueba.gft.prices.application;

import org.prueba.gft.prices.domain.model.Prices;
import org.prueba.gft.prices.domain.repository.PricesRepository;
import org.prueba.gft.prices.domain.service.PricesService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PricesServiceImpl implements PricesService {

	private final PricesRepository pricesRepository;

	public PricesServiceImpl(PricesRepository pricesRepository) {
		this.pricesRepository = pricesRepository;
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
