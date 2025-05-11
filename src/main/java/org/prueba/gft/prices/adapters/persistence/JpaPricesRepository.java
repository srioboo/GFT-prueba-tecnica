package org.prueba.gft.prices.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPricesRepository extends JpaRepository<PricesEntity, Long> {
}
