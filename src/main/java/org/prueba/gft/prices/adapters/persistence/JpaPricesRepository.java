package org.prueba.gft.prices.adapters.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaPricesRepository extends CrudRepository<PricesEntity, Long> {
	List<PricesEntity> findByProductIdAndBrandIdAndStartDate(int productId, int brandId, LocalDateTime startDate);

	List<PricesEntity> findByProductIdAndBrandIdAndStartDateAndEndDate(int productId, int brandId,
																	   LocalDateTime startDate, LocalDateTime endDate);

	List<PricesEntity> findByProductIdAndBrandId(int productId, int brandId);
}
