package org.prueba.gft.prices;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.prueba.gft.prices.domain.Prices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricesRestTest {

	private static final LocalDateTime startTime = LocalDateTime
		.of(2020, 6, 14, 15, 0, 0);

	private static final LocalDateTime endTime = LocalDateTime
		.of(2020, 6, 14, 18, 30, 0);

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void getAllPrices() throws Exception {
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
		assertThat(prices).isNotNull();
		for (Prices price : prices) {
			assertThat(price.getBrandId()).isEqualTo(1);
			assertThat(price.getStartDate()).isEqualTo(startTime);
			assertThat(price.getEndDate()).isEqualTo(endTime);
			assertThat(price.getPriceList()).isEqualTo(2);
			assertThat(price.getProductId()).isEqualTo(35455);
			assertThat(price.getPriority()).isEqualTo(1);
			assertThat(price.getPrice()).isEqualTo(BigDecimal.valueOf(25));
		}
	}
}
