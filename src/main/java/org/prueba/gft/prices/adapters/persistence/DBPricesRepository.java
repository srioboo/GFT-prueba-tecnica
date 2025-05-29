package org.prueba.gft.prices.adapters.persistence;

import lombok.AllArgsConstructor;
import org.prueba.gft.prices.domain.model.Prices;
import org.prueba.gft.prices.domain.repository.PricesRepository;
import org.prueba.gft.prices.mapper.PriceMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DBPricesRepository implements PricesRepository {

	private final JpaPricesRepository jpaPricesRepository;

	@Override
	public Optional<Prices> findByProductIdAndBrandIdByDate(int productId, int brandId, LocalDateTime date) {
		return Optional.ofNullable(PriceMapper.INSTANCE.toDomain(
			jpaPricesRepository.findByProductIdAndBrandIdByDate(productId, brandId, date)));
	}

}
