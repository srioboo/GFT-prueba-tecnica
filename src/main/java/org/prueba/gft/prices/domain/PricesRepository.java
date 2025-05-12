package org.prueba.gft.prices.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesRepository {

    List<Prices> findAll();
    List<Prices> findByProductIdAndBrandIdAndDate(int productId, int brandId, LocalDateTime date);
}
