package org.prueba.gft.prices.application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.prueba.gft.prices.adapters.persistence.PricesEntity;
import org.prueba.gft.prices.domain.model.PriceNotFoundException;
import org.prueba.gft.prices.domain.repository.PricesRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class PricesServiceImplTest {

	@InjectMocks
	private PricesServiceImpl pricesService;

	@Mock
	private PricesRepository pricesRepository;

	private AutoCloseable autoCloseable;
	PricesEntity pricesEntity;

	@BeforeEach
	void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);

		pricesEntity = new PricesEntity();
		pricesEntity.setPrice(BigDecimal.valueOf(23.40));
		pricesEntity.setPriceList(1);
		pricesEntity.setCurr("EUR");
		pricesEntity.setBrandId(1);
		pricesEntity.setProductId(54333);
		pricesEntity.setStartDate(LocalDateTime.MIN);
		pricesEntity.setEndDate(LocalDateTime.MAX);
		pricesEntity.setPriority(1);
	}

	@Test
	void findByProductIdAndBrandIdByDate() throws PriceNotFoundException {
		// TODO - Complete
		//when(pricesRepository.findByProductIdAndBrandIdByDate())
		//	.thenReturn(pricesEntity);
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}
}