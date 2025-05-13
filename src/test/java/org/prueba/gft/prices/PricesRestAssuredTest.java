package org.prueba.gft.prices;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prueba.gft.prices.domain.Prices;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricesRestAssuredTest {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
	}

	@Test
	void shouldGetAllPrices() {
		List<Prices> prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices")
			.then()
			.statusCode(200)
			.extract()
			.body()
			.jsonPath()
			.getList("$", Prices.class);

		assertThat(prices).isNotEmpty();

		Prices price = prices.getFirst();
		assertThat(price.getProductId()).isEqualTo(1);
		assertThat(price.getBrandId()).isEqualTo(2);
		assertThat(price.getPriority()).isEqualTo(1);
		assertThat(price.getPrice()).isEqualTo(BigDecimal.valueOf(25.0));
		assertThat(price.getPriceList()).isEqualTo(1);
		assertThat(price.getStartDate()).isEqualTo(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
		assertThat(price.getEndDate()).isEqualTo(LocalDateTime.of(2020, 12, 1, 0, 0, 0));
		assertThat(price.getCurr()).isEqualTo("EUR");
	}

	@Test
	void shouldGetPriceById() {

	}
}
