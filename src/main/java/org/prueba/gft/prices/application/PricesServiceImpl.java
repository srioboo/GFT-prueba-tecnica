package org.prueba.gft.prices.application;

import lombok.AllArgsConstructor;
import org.prueba.gft.prices.domain.model.PriceNotFoundException;
import org.prueba.gft.prices.domain.model.Prices;
import org.prueba.gft.prices.domain.repository.PricesRepository;
import org.prueba.gft.prices.domain.service.PricesService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PricesServiceImpl implements PricesService {

	private final PricesRepository pricesRepository;

	public Prices findByProductIdAndBrandIdByDate(int productId,
												  int brandId,
												  LocalDateTime date) throws PriceNotFoundException {

		Prices prices = pricesRepository.findByProductIdAndBrandIdByDate(productId, brandId, date);
		Optional<Prices> opPrice = Optional.ofNullable(prices);
		return opPrice.orElse(new Prices());
	}
}
