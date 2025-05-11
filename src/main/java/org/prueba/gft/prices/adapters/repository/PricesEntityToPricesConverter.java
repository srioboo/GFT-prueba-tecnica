package org.prueba.gft.prices.adapters.repository;

import org.prueba.gft.prices.domain.Prices;
import org.springframework.stereotype.Service;

@Service
public class PricesEntityToPricesConverter {
    public Prices convert(PricesEntity pricesEntity) {
        return Prices.builder()
                .productId(pricesEntity.getProductId())
                .price(pricesEntity.getPrice())
                .brandId(pricesEntity.getBrandId())
                .starDate(pricesEntity.getStartDate())
                .endDate(pricesEntity.getEndDate())
                .curr(pricesEntity.getCurr())
                .priority(pricesEntity.getPriority())
                .priceList(pricesEntity.getPriceList())
                .build();
    }
}
