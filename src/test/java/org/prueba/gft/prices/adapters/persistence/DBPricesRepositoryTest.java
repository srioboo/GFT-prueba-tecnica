package org.prueba.gft.prices.adapters.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.prueba.gft.prices.domain.model.Prices;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class DBPricesRepositoryTest {

	@InjectMocks
	private DBPricesRepository dbPricesRepository;

	private Prices mockPrices;

	@BeforeEach
	void setUp() {
		dbPricesRepository = Mockito.mock(DBPricesRepository.class);
		mockPrices = Prices.builder()
				.productId(1)
				.brandId(1)
				.productId(54333)
				.curr("EUR")
				.priceList(1)
				.priority(1)
				.startDate("2020-06-14-10.00.00")
				.endDate("2020-06-15-10.00.00").build();
	}

	@DisplayName("Test find product")
	@Test
	void findByProductIdAndBrandIdByDate() {
		when(dbPricesRepository.findByProductIdAndBrandIdByDate(anyInt(), anyInt(), any(LocalDateTime.class)))
				.thenReturn(Optional.ofNullable(mockPrices));
		assertNotNull(mockPrices);
	}
}