package org.prueba.gft.prices.adapters.persistence;

import org.prueba.gft.prices.domain.model.Prices;
import org.prueba.gft.prices.domain.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DBPricesRepository implements PricesRepository {

	private final JpaPricesRepository jpaPricesRepository;
	private final PricesEntityToPricesConverter pricesEntityToPricesConverter;

	@Autowired
	public DBPricesRepository(JpaPricesRepository jpaPricesRepository,
							  PricesEntityToPricesConverter pricesEntityToPricesConverter) {
		this.jpaPricesRepository = jpaPricesRepository;
		this.pricesEntityToPricesConverter = pricesEntityToPricesConverter;
	}
	
	@Override
	public List<Prices> findByProductIdAndBrandIdByDate(int productId, int brandId, LocalDateTime date) {
		List<PricesEntity> pricesEntities = jpaPricesRepository
			.findByProductIdAndBrandIdByDate(productId, brandId, date);
		return pricesEntities.stream().map(pricesEntityToPricesConverter::convert).toList();
	}

}
