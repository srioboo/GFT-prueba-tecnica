package org.prueba.gft.prices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prueba.gft.prices.adapters.persistence.DBPricesRepository;
import org.prueba.gft.prices.domain.model.Prices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PriceControllerIntegrationTest {

	@Container
	static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13.2")
		.withDatabaseName("testdb")
		.withUsername("integration")
		.withPassword("password");

	@DynamicPropertySource
	static void overrideProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
		registry.add("spring.datasource.driver-class-name", postgreSQLContainer::getDriverClassName);
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private DBPricesRepository dbPricesRepository;

	private Prices prices;

	@BeforeEach
	void setUp() {

		prices = Prices.builder()
			.brandId(1)
			.productId(55555)
			.price(BigDecimal.valueOf(34.50))
			.priceList(1)
			.priority(1)
			.curr("EUR")
			.startDate("")
			.endDate("")
			.build();
	}

	@Test
	void shouldFailReturnPriceForValidRequest() throws Exception {
		mockMvc.perform(get("/prices/brand/1/product/35455")
				.param("date", "2020-06-14-15.00.00"))
			.andExpect(status().isNotFound());
	}
}
