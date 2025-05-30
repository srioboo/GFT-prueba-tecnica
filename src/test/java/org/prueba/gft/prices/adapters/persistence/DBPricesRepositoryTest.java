package org.prueba.gft.prices.adapters.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.prueba.gft.prices.application.PricesServiceImpl;
import org.prueba.gft.prices.domain.model.PriceNotFoundException;
import org.prueba.gft.prices.domain.model.Prices;
import org.prueba.gft.prices.domain.repository.PricesRepository;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class DBPricesRepositoryTest {

	@Mock
	private PricesRepository pricesRepository;

	@InjectMocks
	private PricesServiceImpl pricesService;

	private Prices mockPrices;

	@BeforeEach
	void setUp() {
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
	void findByProductIdAndBrandIdByDate() throws PriceNotFoundException {
		Mockito.when(pricesRepository.findByProductIdAndBrandIdByDate(anyInt(), anyInt(), any(LocalDateTime.class)))
			.thenReturn(Optional.ofNullable(mockPrices));
		Prices price = pricesService.findByProductIdAndBrandIdByDate(54333, 1, LocalDateTime.now());
		assertNotNull(mockPrices);
		assertEquals(mockPrices, price);
	}

	@DisplayName("Test product not found")
	@Test
	void handlePriceNotFound() {
		Mockito.when(pricesRepository.findByProductIdAndBrandIdByDate(anyInt(), anyInt(), any()))
			.thenReturn(Optional.empty());
		assertThrows(PriceNotFoundException.class, () -> {
			pricesService.findByProductIdAndBrandIdByDate(anyInt(), anyInt(), any(LocalDateTime.class));
		});
	}
}