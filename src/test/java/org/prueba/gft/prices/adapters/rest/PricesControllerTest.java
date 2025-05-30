package org.prueba.gft.prices.adapters.rest;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.prueba.gft.prices.domain.model.DateFormatIncorrectException;
import org.prueba.gft.prices.domain.model.PriceNotFoundException;
import org.prueba.gft.prices.domain.service.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PricesControllerTest {
	
	public static final String WRONG_DATE = "2020-0614 10.00.00";
	public static final String DATE = "2020-06-14-16.00.00";

	@InjectMocks
	private PricesController pricesController;

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private PricesService pricesService;

	private AutoCloseable autoCloseable;

	@BeforeEach
	void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);
	}

	@DisplayName("Test incorrect Date")
	@Test
	void handleIncorrectDateFound() {
		assertThrows(DateFormatIncorrectException.class, () -> {
			pricesController.getPricesByDateProductIdBrandId(1, 1, WRONG_DATE);
		});
	}

	@DisplayName("Test price not found")
	@Test
	void handlePriceNotFound() throws Exception {
		Mockito.when(pricesService.findByProductIdAndBrandIdByDate(anyInt(), anyInt(), any()))
			.thenThrow(PriceNotFoundException.class);
		mockMvc.perform(get("/prices/brand/1/product/35455")
				.param("date", DATE))
			.andExpect(status().isNotFound())
			.andExpect(result -> Assertions.assertTrue(result
				.getResolvedException() instanceof PriceNotFoundException));
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}
}