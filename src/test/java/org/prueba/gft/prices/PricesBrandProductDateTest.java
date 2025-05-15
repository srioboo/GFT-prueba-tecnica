package org.prueba.gft.prices;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.prueba.gft.prices.domain.Prices;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureJsonTesters
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PricesBrandProductDateTest {

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@ParameterizedTest(name = "{index} - at date {2} product {1} brand {0} is {3}")
	@CsvSource({
		"'1', '35455', '2020-06-14-10.00.00', 35.50",
		"'1', '35455', '2020-06-14-16.00.00', 25.45",
		"'1', '35455', '2020-06-14-21.00.00', 35.50",
		"'1', '35455', '2020-06-15-10.00.00', 30.50",
		"'1', '35455', '2020-06-16-21.00.00', 38.95",
		"'2', '35454', '2020-06-14-10.00.00', 35.50",
		"'2', '35454', '2020-06-14-15.00.00', 25.45",
		"'2', '35454', '2020-06-14-16.00.00', 25.45",
		"'3', '35458', '2025-09-14-19.00.00', 130.50",
		"'3', '35458', '2025-03-15-15.00.00', 130.50",
		"'3', '35459', '2025-03-14-01.00.00', 250.50",
		"'3', '35459', '2025-03-15-16.01.00', 32.95",
	})
	void shouldGetPriceByBrandProductIdAndDate(String brand, String product, String date, BigDecimal price) {
		String restUrl = "/prices/brand/" + brand + "/product/" + product + "?date=" + date;
		Prices prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get(restUrl)
			.then()
			.statusCode(200)
			.extract()
			.as(Prices.class);
		assertThat(prices).isNotNull();
		assertThat(prices.getPrice()).isEqualTo(price
			.setScale(2, RoundingMode.UNNECESSARY));
	}


	@ParameterizedTest(name = "{index} - there are not prices for {1} at date {2} for brand {0}")
	@CsvSource({
		"'3', '10009', '2025-03-15-16.01.00'",
		"'4', '35459', '2025-03-15-16.01.00'",
	})
	void nullResponse(String brand, String product, String date) {
		String restUrl = "/prices/brand/" + brand + "/product/" + product + "?date=" + date;
		Prices prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get(restUrl)
			.then()
			.statusCode(200)
			.extract()
			.as(Prices.class);
		assertThat(prices).isNotNull();
		assertThat(prices.getPrice()).isNull();
	}
}
