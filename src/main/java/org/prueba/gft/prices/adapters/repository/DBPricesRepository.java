package org.prueba.gft.prices.adapters.repository;

import org.prueba.gft.prices.domain.Prices;
import org.prueba.gft.prices.domain.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public List<Prices> findAll() {
        List<PricesEntity> pricesEntities = jpaPricesRepository.findAll();
        return pricesEntities.stream()
                .map(pricesEntityToPricesConverter::convert)
                .toList();
    }
}
