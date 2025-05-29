package org.prueba.gft.prices.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface JpaPricesRepository extends JpaRepository<PricesEntity, Long> {

	@Query(value = "SELECT p FROM PricesEntity p " +
		"WHERE p.brandId = ?2 " +
		"AND p.productId = ?1 " +
		"AND (p.startDate <= ?3 AND p.endDate > ?3) " +
		"ORDER BY p.priority desc LIMIT 1")
	PricesEntity findByProductIdAndBrandIdByDate(int productId, int brandId, LocalDateTime date);
}
