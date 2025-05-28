package org.prueba.gft.prices.application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.prueba.gft.prices.domain.model.PriceNotFoundException;
import org.prueba.gft.prices.domain.model.Prices;
import org.prueba.gft.prices.domain.repository.PricesRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class PricesServiceImplTest {

	@InjectMocks
	private PricesServiceImpl pricesService;

	@Mock
	private PricesRepository pricesRepository;

	private AutoCloseable autoCloseable;
	private Prices prices;

	@BeforeEach
	void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);
		prices = Prices.builder()
			.price(BigDecimal.valueOf(23.40))
			.priceList(1)
			.curr("EUR")
			.brandId(1)
			.productId(54333)
			.startDate("2025-03-15-16.01.00")
			.endDate("2025-03-15-16.01.00")
			.priority(1)
			.build();
	}

	@DisplayName("Test findByProductIdAndBrandIdByDate in service")
	@Test
	void findByProductIdAndBrandIdByDate() throws PriceNotFoundException {
		when(pricesService.findByProductIdAndBrandIdByDate(anyInt(), anyInt(), any(LocalDateTime.class)))
			.thenReturn(prices);
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}
}