package org.prueba.gft.prices.domain.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {
	PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

	PricesDTO pricesToPricesDTO(Prices prices);

	Prices pricesDTOToPrices(PricesDTO pricesDTO);
}
