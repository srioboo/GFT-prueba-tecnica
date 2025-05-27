package org.prueba.gft.prices.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.prueba.gft.prices.adapters.persistence.PricesEntity;
import org.prueba.gft.prices.domain.dto.PricesDTO;
import org.prueba.gft.prices.domain.model.Prices;

@Mapper(componentModel = "spring")
public interface PriceMapper {
	PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

	Prices toDomain(PricesEntity pricesEntity);

	PricesEntity toEntity(Prices prices);

	PricesDTO toDTO(Prices prices);

	Prices fromDTO(PricesDTO pricesDTO);
}
