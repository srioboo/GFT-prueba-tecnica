package org.prueba.gft.prices.adapters.persistence;

import org.prueba.gft.prices.domain.mapper.PriceMapper;
import org.prueba.gft.prices.domain.model.PriceNotFoundException;
import org.prueba.gft.prices.domain.model.Prices;
import org.prueba.gft.prices.domain.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DBPricesRepository implements PricesRepository {

	private final JpaPricesRepository jpaPricesRepository;

	@Autowired
	public DBPricesRepository(JpaPricesRepository jpaPricesRepository) {
		this.jpaPricesRepository = jpaPricesRepository;
	}

	@Override
	public Prices findByProductIdAndBrandIdByDate(int productId, int brandId, LocalDateTime date) throws PriceNotFoundException {
		List<PricesEntity> pricesEntities = jpaPricesRepository
			.findByProductIdAndBrandIdByDate(productId, brandId, date);
		if (pricesEntities.isEmpty())
			throw new PriceNotFoundException("not found");
		return pricesEntities.stream().map(PriceMapper.INSTANCE::toDomain).toList().getFirst();
	}

}
