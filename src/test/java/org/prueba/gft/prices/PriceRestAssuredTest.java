package org.prueba.gft.prices;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prueba.gft.prices.domain.model.Prices;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureJsonTesters
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceRestAssuredTest {

	private static final String JSON_PATH = "/json/prices-first.json";
	private ObjectContent<Prices> jsonContent;
	private JacksonTester<Prices> jsonTester;

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() throws IOException {
		RestAssured.port = port;
		ObjectMapper objectMapper = new ObjectMapper();
		JacksonTester.initFields(this, objectMapper);
		jsonContent = jsonTester.read(getClass().getResourceAsStream(JSON_PATH));
	}

	@DisplayName("Endpoint prices return a Price Object data equals to prices-first.json example")
	@Test
	void shouldGetPrice() {
		Prices price = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.get("/prices/brand/1/product/35455?date=2020-06-14-10.00.00")
			.then()
			.statusCode(200)
			.extract()
			.as(Prices.class);
		Prices jsonPrice = jsonContent.getObject();
		assertThat(price.getProductId()).isEqualTo(jsonPrice.getProductId());
		assertThat(price.getBrandId()).isEqualTo(jsonPrice.getBrandId());
		assertThat(price.getPriority()).isEqualTo(jsonPrice.getPriority());
		assertThat(price.getPrice()).isEqualTo(jsonPrice.getPrice());
		assertThat(price.getPriceList()).isEqualTo(jsonPrice.getPriceList());
		assertThat(price.getCurr()).isEqualTo(jsonPrice.getCurr());
	}

}
