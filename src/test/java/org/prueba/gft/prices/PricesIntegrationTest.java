package org.prueba.gft.prices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prueba.gft.prices.adapters.persistence.DBPricesRepository;
import org.prueba.gft.prices.adapters.persistence.JpaPricesRepository;
import org.prueba.gft.prices.domain.model.Prices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Integration Test with container")
@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class PricesIntegrationTest {

	@Autowired
	private DBPricesRepository dbPricesRepository;

	@Autowired
	private JpaPricesRepository repository;

	@Autowired
	private MockMvc mockMvc;

	@DisplayName("Test failed getting price")
	@Test
	void shouldFailReturnPriceForValidRequest() throws Exception {
		mockMvc.perform(get("/prices/brand/1/product/35455")
				.param("date", "2020-06-14-15.00.00"))
			.andExpect(status().isNotFound());
	}

	@DisplayName("Test price is correct")
	@Sql(scripts = {"classpath:data-integration.sql"})
	@Test
	void shouldGetPrice() {
		Optional<Prices> price = dbPricesRepository
			.findByProductIdAndBrandIdByDate(35455, 1, LocalDateTime.of(2020, 6, 14, 10, 0));
		assertThat(price).isPresent();
		price.ifPresent(prices -> assertThat(prices.getPrice()).isEqualTo(BigDecimal.valueOf(35.5)
			.setScale(2, RoundingMode.UNNECESSARY)));
	}

	@DisplayName("Test table count is not 0")
	@Test
	void shouldHavePrices() {
		Long count = repository.count();
		Assertions.assertNotEquals(0, count);
	}
}
