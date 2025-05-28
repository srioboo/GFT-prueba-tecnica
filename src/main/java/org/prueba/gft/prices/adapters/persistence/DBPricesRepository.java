package org.prueba.gft.prices.adapters.persistence;

import lombok.AllArgsConstructor;
import org.prueba.gft.prices.domain.model.Prices;
import org.prueba.gft.prices.domain.repository.PricesRepository;
import org.prueba.gft.prices.mapper.PriceMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class DBPricesRepository implements PricesRepository {

	private final JpaPricesRepository jpaPricesRepository;

	@Override
	public Prices findByProductIdAndBrandIdByDate(int productId, int brandId, LocalDateTime date) {
		List<PricesEntity> pricesEntities = jpaPricesRepository
			.findByProductIdAndBrandIdByDate(productId, brandId, date)
			.stream().toList();
		return pricesEntities.stream().map(PriceMapper.INSTANCE::toDomain).toList().getFirst();
	}

}
