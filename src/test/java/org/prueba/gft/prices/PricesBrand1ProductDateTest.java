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
class PricesBrand1ProductDateTest {

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@DisplayName("14 at 10:00 product 35455 brand 1 is 35.50")
	@Test
	void shouldGetPriceByBrandProductIdAnd1410() {
		Prices prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/1/product/35455?date=2020-06-14-10.00.00")
			.then()
			.statusCode(200)
			.extract()
			.as(Prices.class);
		assertThat(prices).isNotNull();
		assertThat(prices.getPrice()).isEqualTo(BigDecimal.valueOf(35.50)
			.setScale(2, RoundingMode.UNNECESSARY));
	}

	@DisplayName("14 at 16:00 product 35455 brand 1 is 25.45")
	@Test
	void shouldGetPriceByBrandProductIdAnd1416() {
		Prices prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/1/product/35455?date=2020-06-14-16.00.00")
			.then()
			.statusCode(200)
			.extract()
			.as(Prices.class);
		assertThat(prices).isNotNull();
		assertThat(prices.getPrice()).isEqualTo(BigDecimal.valueOf(25.45)
			.setScale(2, RoundingMode.UNNECESSARY));
	}

	@DisplayName("14 at 21:00 product 35455 brand 1 is 35.50")
	@Test
	void shouldGetPriceByBrandProductIdAnd1421() {
		Prices prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/1/product/35455?date=2020-06-14-21.00.00")
			.then()
			.statusCode(200)
			.extract()
			.as(Prices.class);
		assertThat(prices).isNotNull();
		assertThat(prices.getPrice()).isEqualTo(BigDecimal.valueOf(35.50)
			.setScale(2, RoundingMode.UNNECESSARY));
	}

	@DisplayName("15 at 10:00 product 35455 brand 1 is 30.50")
	@Test
	void shouldGetPriceByBrandProductIdAnd1510() {
		Prices prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/1/product/35455?date=2020-06-15-10.00.00")
			.then()
			.statusCode(200)
			.extract()
			.as(Prices.class);
		assertThat(prices).isNotNull();
		assertThat(prices.getPrice()).isEqualTo(BigDecimal.valueOf(30.50)
			.setScale(2, RoundingMode.UNNECESSARY));
	}

	@DisplayName("16 at 21:00 product 35455 brand 1 is 38.95")
	@Test()
	void shouldGetPriceByBrandProductIdAnd1621() {
		Prices prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/1/product/35455?date=2020-06-16-21.00.00")
			.then()
			.statusCode(200)
			.extract()
			.as(Prices.class);
		assertThat(prices).isNotNull();
		assertThat(prices.getPrice()).isEqualTo(BigDecimal.valueOf(38.95)
			.setScale(2, RoundingMode.UNNECESSARY));
	}
}
