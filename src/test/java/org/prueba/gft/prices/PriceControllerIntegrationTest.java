package org.prueba.gft.prices;

import org.junit.jupiter.api.BeforeEach;
import org.prueba.gft.prices.adapters.persistence.DBPricesRepository;
import org.prueba.gft.prices.domain.model.Prices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.math.BigDecimal;

// @Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PriceControllerIntegrationTest {

	@Container
	static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13.2")
		.withDatabaseName("testdb")
		.withUsername("integration")
		.withPassword("password");

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


}
