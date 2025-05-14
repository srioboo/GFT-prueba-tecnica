package org.prueba.gft.prices;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prueba.gft.prices.domain.Prices;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureJsonTesters
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricesRestAssuredTest {

	private static final String JSON_PATH = "/json/prices-first.json";
	private ObjectContent<Prices> jsonContent;
	private JacksonTester<Prices> jsonTester;

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() throws IOException {
		RestAssured.port = port;
		ObjectMapper objectMapper = new ObjectMapper();
		JacksonTester.initFields(this, objectMapper);
		jsonContent = jsonTester.read(getClass().getResourceAsStream(JSON_PATH));
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
		Prices jsonPrice = jsonContent.getObject();
		Prices price = prices.getFirst();
		assertThat(price.getProductId()).isEqualTo(jsonPrice.getProductId());
		assertThat(price.getBrandId()).isEqualTo(jsonPrice.getBrandId());
		assertThat(price.getPriority()).isEqualTo(jsonPrice.getPriority());
		assertThat(price.getPrice()).isEqualTo(jsonPrice.getPrice());
		assertThat(price.getPriceList()).isEqualTo(jsonPrice.getPriceList());
		assertThat(price.getStartDate()).isEqualTo(jsonPrice.getStartDate());
		assertThat(price.getEndDate()).isEqualTo(jsonPrice.getEndDate());
		assertThat(price.getCurr()).isEqualTo(jsonPrice.getCurr());
	}

	@DisplayName("14 at 10:00")
	@Test
	void shouldGetPriceByBrandProductIdAnd1410() {
		List<Prices> prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/1/product/35455?date=2020-06-14-10.00.00")
			.then()
			.statusCode(200)
			.extract()
			.body()
			.jsonPath()
			.getList("$", Prices.class);

		assertThat(prices).isNotEmpty();

		Prices price = prices.getFirst(); // TODO - FILTER CORRECT PRICE

		assertThat(price.getPrice()).isEqualTo(BigDecimal.valueOf(35.50));
	}

	@DisplayName("14 at 16:00")
	@Test
	void shouldGetPriceByBrandProductIdAnd1416() {
		List<Prices> prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/1/product/35455?date=2020-06-14-16.00.00")
			.then()
			.statusCode(200)
			.extract()
			.body()
			.jsonPath()
			.getList("$", Prices.class);

		assertThat(prices).isNotEmpty();

		Prices price = prices.getFirst(); // TODO - FILTER CORRECT PRICE

		assertThat(price.getPrice()).isEqualTo(BigDecimal.valueOf(25.45));
	}

	@DisplayName("14 at 21:00")
	@Test
	void shouldGetPriceByBrandProductIdAnd1421() {
		List<Prices> prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/1/product/35455?date=2020-06-14-21.00.00")
			.then()
			.statusCode(200)
			.extract()
			.body()
			.jsonPath()
			.getList("$", Prices.class);

		assertThat(prices).isNotEmpty();

		Prices price = prices.getFirst(); // TODO - FILTER CORRECT PRICE

		assertThat(price.getPrice()).isEqualTo(BigDecimal.valueOf(35.50));
	}

	@DisplayName("15 at 10:00")
	@Test
	void shouldGetPriceByBrandProductIdAnd1510() {
		List<Prices> prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/1/product/35455?date=2020-06-15-10.00.00")
			.then()
			.statusCode(200)
			.extract()
			.body()
			.jsonPath()
			.getList("$", Prices.class);

		assertThat(prices).isNotEmpty();

		Prices price = prices.getFirst(); // TODO - FILTER CORRECT PRICE

		assertThat(price.getPrice()).isEqualTo(BigDecimal.valueOf(30.50));
	}

	@DisplayName("16 at 21:00")
	@Test()
	void shouldGetPriceByBrandProductIdAnd1621() {
		List<Prices> prices = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/1/product/35455?date=2020-06-16-21.00.00")
			.then()
			.statusCode(200)
			.extract()
			.body()
			.jsonPath()
			.getList("$", Prices.class);

		assertThat(prices).isNotEmpty();

		Prices price = prices.getFirst(); // TODO - FILTER CORRECT PRICE

		assertThat(price.getPrice()).isEqualTo(BigDecimal.valueOf(38.95));
	}
}
