package org.prueba.gft.prices.adapters.persistence;

import lombok.AllArgsConstructor;
import org.prueba.gft.prices.application.DateUtils;
import org.prueba.gft.prices.domain.model.Prices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PricesEntityToPricesConverter {

	private final DateUtils dateUtils;

	public Prices convert(PricesEntity pricesEntity) {
		return Prices.builder()
			.productId(pricesEntity.getProductId())
			.price((pricesEntity.getPrice()).setScale(2, RoundingMode.UNNECESSARY))
			.brandId(pricesEntity.getBrandId())
			.startDate(dateUtils.transformDate(pricesEntity.getStartDate()))
			.endDate(dateUtils.transformDate(pricesEntity.getEndDate()))
			.curr(pricesEntity.getCurr())
			.priority(pricesEntity.getPriority())
			.priceList(pricesEntity.getPriceList())
			.build();
	}
}
