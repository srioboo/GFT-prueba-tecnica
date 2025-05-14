package org.prueba.gft.prices;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prueba.gft.prices.domain.Prices;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
		assertThat(price.getProductId()).isEqualTo(35455);
		assertThat(price.getBrandId()).isEqualTo(1);
		assertThat(price.getPriority()).isEqualTo(1);
		assertThat(price.getPrice()).isEqualTo(BigDecimal.valueOf(25.0).setScale(1, RoundingMode.HALF_UP));
		assertThat(price.getPriceList()).isEqualTo(1);
		assertThat(price.getStartDate()).isEqualTo("2020-01-01-00.00.00");
		assertThat(price.getEndDate()).isEqualTo("2020-01-01-00.00.00");
		assertThat(price.getCurr()).isEqualTo("EUR");
	}

	@Test
	void shouldGetPriceById() {

	}
}
