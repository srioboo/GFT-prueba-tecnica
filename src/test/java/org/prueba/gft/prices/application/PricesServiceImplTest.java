package org.prueba.gft.prices.application;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.prueba.gft.prices.domain.model.PriceNotFoundException;
import org.prueba.gft.prices.domain.model.Prices;
import org.prueba.gft.prices.domain.repository.PricesRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
		pricesRepository = Mockito.mock(PricesRepository.class);
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
	@Disabled
	void findByProductIdAndBrandIdByDate() throws PriceNotFoundException {
		when(pricesService.findByProductIdAndBrandIdByDate(anyInt(), anyInt(), any(LocalDateTime.class)))
			.thenReturn(prices);
		assertNotNull(prices);
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}
}