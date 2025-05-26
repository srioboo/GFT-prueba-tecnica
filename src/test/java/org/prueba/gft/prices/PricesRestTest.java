package org.prueba.gft.prices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prueba.gft.prices.domain.model.Prices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureJsonTesters
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PricesRestTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@DisplayName("Endpoint prices return a list of object Prices not null")
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

}
