package org.prueba.gft.prices.adapters.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.prueba.gft.prices.application.PricesServiceImpl;
import org.prueba.gft.prices.domain.model.PriceNotFoundException;
import org.prueba.gft.prices.domain.model.Prices;

class PricesControllerTest {

	@InjectMocks
	PricesController pricesController;

	@Mock
	PricesServiceImpl pricesService;

	Prices prices;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		prices = new Prices();
		prices.setProductId(1);
		prices.setBrandId(1);
		prices.setProductId(54333);
		prices.setCurr("EUR");
		prices.setPriceList(1);
		prices.setPriority(1);
		prices.setStartDate("2020-06-14-10.00.00");
		prices.setEndDate("2020-06-15-10.00.00");
	}

	@Test
	@Disabled
	void handlePriceNotFound() throws PriceNotFoundException {
		// TODO
		// when(pricesService.findByProductIdAndBrandIdByDate(1, 1, LocalDateTime.now())).thenThrow(new PriceNotFoundException(""));
		//assertThrows(PriceNotFoundException.class, () -> {
		//	when(pricesService.findByProductIdAndBrandIdByDate(1, 1, LocalDateTime.now())).thenReturn(null);
		//	when(pricesService.findByProductIdAndBrandIdByDate(1, 1, LocalDateTime.now())).thenThrow(PriceNotFoundException.class);
		//});
	}

	@Test
	@Disabled
	void getPricesByDateProductIdBrandId() throws PriceNotFoundException {
		// TODO
		//when(pricesService.findByProductIdAndBrandIdByDate(1, 1, LocalDateTime.now())).thenReturn(prices);
		//assertEquals();

	}
}