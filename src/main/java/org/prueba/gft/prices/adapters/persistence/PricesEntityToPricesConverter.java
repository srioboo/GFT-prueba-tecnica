package org.prueba.gft.prices.adapters.persistence;

import org.prueba.gft.prices.application.DateUtils;
import org.prueba.gft.prices.domain.Prices;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;

@Service
public class PricesEntityToPricesConverter {
	public Prices convert(PricesEntity pricesEntity) {
		return Prices.builder()
			.productId(pricesEntity.getProductId())
			.price((pricesEntity.getPrice()).setScale(2, RoundingMode.UNNECESSARY))
			.brandId(pricesEntity.getBrandId())
			.startDate(DateUtils.transformDate(pricesEntity.getStartDate()))
			.endDate(DateUtils.transformDate(pricesEntity.getEndDate()))
			.curr(pricesEntity.getCurr())
			.priority(pricesEntity.getPriority())
			.priceList(pricesEntity.getPriceList())
			.build();
	}
}
