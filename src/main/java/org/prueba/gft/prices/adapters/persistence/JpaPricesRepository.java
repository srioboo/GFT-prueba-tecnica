package org.prueba.gft.prices.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaPricesRepository extends JpaRepository<PricesEntity, Long> {

	@Query(value = "SELECT p FROM PricesEntity p " +
		"WHERE p.productId = ?1 " +
		"and p.brandId = ?2 " +
		"and (p.startDate <= ?3 and p.endDate > ?3)")
	List<PricesEntity> findByProductIdAndBrandIdByDate(int productId, int brandId, LocalDateTime date);
}
