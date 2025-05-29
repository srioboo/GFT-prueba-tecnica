package org.prueba.gft.prices.adapters.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.prueba.gft.prices.application.PricesServiceImpl;
import org.prueba.gft.prices.domain.model.DateFormatIncorrectException;
import org.prueba.gft.prices.domain.model.PriceNotFoundException;
import org.prueba.gft.prices.domain.model.Prices;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PricesControllerTest {

	@InjectMocks
	PricesController pricesController;

	@Mock
	PricesServiceImpl pricesService;

	Prices prices;
	PricesDTO pricesDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		prices = Prices.builder()
			.brandId(1)
			.productId(54333)
			.curr("EUR")
			.priceList(1)
			.priority(1)
			.startDate("2020-06-14-10.00.00")
			.endDate("2020-06-15-10.00.00")
			.build();

		pricesDTO = PricesDTO.builder()
			.brandId(2)
			.productId(54335)
			.curr("EUR")
			.priceList(1)
			.priority(1)
			.startDate("2020-06-14-10.00.00")
			.endDate("2020-06-15-10.00.00")
			.build();
	}

	@DisplayName("Test incorrect Date")
	@Test
	void handleIncorrectDateFound() {
		assertThrows(DateFormatIncorrectException.class, () -> {
			pricesController.getPricesByDateProductIdBrandId(1, 1, "2020-06-14-10.00.00   ");
		});
	}

	@DisplayName("Test price not found")
	@Test
	@Disabled
	void handlePriceNotFound() {
		assertThrows(PriceNotFoundException.class, () -> {
			pricesController.getPricesByDateProductIdBrandId(1, 1, "2020-06-14-10.00.00");
		});
	}
}