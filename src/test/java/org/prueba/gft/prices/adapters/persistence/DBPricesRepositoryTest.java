package org.prueba.gft.prices.adapters.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.prueba.gft.prices.application.DateUtils;
import org.prueba.gft.prices.domain.model.PriceNotFoundException;
import org.prueba.gft.prices.domain.model.Prices;
import org.prueba.gft.prices.domain.repository.PricesRepository;
import org.prueba.gft.prices.domain.service.PricesService;

import static org.mockito.Mockito.when;

class DBPricesRepositoryTest {

	//@InjectMocks
	//private DBPricesRepository dbPricesRepository;

	@Mock
	private PricesService pricesService;


	@BeforeEach
	void setUp() {
	}

	@Test
	@Disabled
	void findByProductIdAndBrandIdByDate() throws PriceNotFoundException {

		PricesRepository mockPricesRepository = Mockito.mock(PricesRepository.class);

		Prices mockPrices = new Prices();
		mockPrices.setProductId(1);
		mockPrices.setBrandId(1);
		mockPrices.setProductId(54333);
		mockPrices.setCurr("EUR");
		mockPrices.setPriceList(1);
		mockPrices.setPriority(1);
		mockPrices.setStartDate("2020-06-14-10.00.00");
		mockPrices.setEndDate("2020-06-15-10.00.00");

		DateUtils dateUtils = new DateUtils();

		when(mockPricesRepository.findByProductIdAndBrandIdByDate(54333, 1, dateUtils.prepareDate("2020-06-14-10.00.00")))
				.thenReturn(mockPrices);

		// TODO correcto PriceDTO
		// Prices result = pricesService
		//		.findByProductIdAndBrandIdByDate(54333, 1, dateUtils.prepareDate("2020-06-14-10.00.00"));
		//assertNotNull(result);
		//assertEquals(mockPrices, result);
	}
}