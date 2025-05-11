package org.prueba.gft.prices.application;

import org.prueba.gft.prices.domain.Prices;
import org.prueba.gft.prices.domain.PricesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricesService {

    private final PricesRepository pricesRepository;

    public PricesService(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    public List<Prices> findAll() {
        return pricesRepository.findAll();
    }
}
