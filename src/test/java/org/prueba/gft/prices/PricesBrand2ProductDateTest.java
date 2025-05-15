package org.prueba.gft.prices;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prueba.gft.prices.domain.Prices;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureJsonTesters
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricesBrand2ProductDateTest {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
	}

	@DisplayName("14 at 10:00 product 35454 brand 2 is 35.50")
	@Test
	void shouldGetPriceByBrandProductIdAnd1410() {
		Prices prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/2/product/35454?date=2020-06-14-10.00.00")
			.then()
			.statusCode(200)
			.extract()
			.as(Prices.class);
		assertThat(prices).isNotNull();
		assertThat(prices.getPrice()).isEqualTo(BigDecimal.valueOf(35.50)
			.setScale(2, RoundingMode.UNNECESSARY));
	}

	@DisplayName("14 at 15:00 product 35454 brand 2 is 25.45")
	@Test
	void shouldGetPriceByBrandProductIdAnd1416() {
		Prices prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/2/product/35454?date=2020-06-14-16.00.00")
			.then()
			.statusCode(200)
			.extract()
			.as(Prices.class);
		assertThat(prices).isNotNull();
		assertThat(prices.getPrice()).isEqualTo(BigDecimal.valueOf(25.45)
			.setScale(2, RoundingMode.UNNECESSARY));
	}

	@DisplayName("14 at 16:00 product 35454 brand 2 is 25.45")
	@Test
	void shouldGetPriceByBrandProductIdAnd1421() {
		Prices prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/2/product/35454?date=2020-06-14-16.00.00")
			.then()
			.statusCode(200)
			.extract()
			.as(Prices.class);
		assertThat(prices).isNotNull();
		assertThat(prices.getPrice()).isEqualTo(BigDecimal.valueOf(25.45)
			.setScale(2, RoundingMode.UNNECESSARY));
	}

}
