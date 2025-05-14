package org.prueba.gft.prices;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prueba.gft.prices.domain.Prices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureJsonTesters
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricesRestTest {
	
	private static final String JSON_PATH = "/json/prices.json";
	private ObjectContent<Prices> jsonContent;
	private JacksonTester<Prices> jsonTester;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	public void setUp() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JacksonTester.initFields(this, objectMapper);
		jsonContent = jsonTester.read(getClass().getResourceAsStream(JSON_PATH));
	}

	@Test
	void getAllPrices() {
		ResponseEntity<Prices[]> response = restTemplate
			.getForEntity("/prices", Prices[].class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		Prices[] prices = response.getBody();
		assertThat(prices).isNotNull();
		for (Prices price : prices) {
			assertThat(price).isNotNull();
		}
	}

	@Test
	void getPrice() throws Exception {
		ResponseEntity<Prices[]> response = restTemplate
			.getForEntity("/prices/brand/1/product/35455?start-date=2020-06-14-15.00.00&end-date=2020-06-16-15.00.00", Prices[].class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Prices[] prices = response.getBody();
		Prices jsonPrice = jsonContent.getObject();

		assertThat(prices).isNotNull();
		for (Prices price : prices) {
			assertThat(price.getBrandId()).isEqualTo(jsonPrice.getBrandId());
			assertThat(price.getStartDate()).isEqualTo(jsonPrice.getStartDate());
			assertThat(price.getEndDate()).isEqualTo(jsonPrice.getEndDate());
			assertThat(price.getPriceList()).isEqualTo(jsonPrice.getPriceList());
			assertThat(price.getProductId()).isEqualTo(jsonPrice.getProductId());
			assertThat(price.getPriority()).isEqualTo(jsonPrice.getPriority());
			assertThat(price.getPrice()).isEqualTo(jsonPrice.getPrice());
		}
	}
}
