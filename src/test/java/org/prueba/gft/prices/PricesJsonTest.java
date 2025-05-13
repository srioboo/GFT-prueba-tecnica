package org.prueba.gft.prices;

import org.junit.jupiter.api.Test;
import org.prueba.gft.prices.domain.Prices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class PricesJsonTest {

	private static final LocalDateTime testTime = LocalDateTime
		.of(2020, 6, 16, 15, 0, 0);

	private static final Prices PRICES = new Prices(3, testTime,
		testTime, 1, 35456, 1, BigDecimal.valueOf(38.95), "EUR");

	private static final String JSON_PRICE = """
		{
			"brandId": 3,
			"startDate": "2020-06-16T15:00:00",
			"endDate": "2020-06-16T15:00:00",
			"priceList": 1,
			"productId": 35456,
			"priority": 1,
			"price": 38.95,
			"curr": "EUR"
		  }
		""";

	@Autowired
	private JacksonTester<Prices> json;

	@Test
	void serializesToJson() throws IOException {
		JsonContent<Prices> jsonContent = json.write(PRICES);

		assertThat(jsonContent).hasJsonPathNumberValue("@.brandId");
		assertThat(jsonContent).hasJsonPathStringValue("@.startDate");
		assertThat(jsonContent).hasJsonPathStringValue("@.endDate");
		assertThat(jsonContent).hasJsonPathNumberValue("@.priceList");
		assertThat(jsonContent).hasJsonPathNumberValue("@.productId");
		assertThat(jsonContent).hasJsonPathNumberValue("@.priority");
		assertThat(jsonContent).hasJsonPathNumberValue("@.price");
		assertThat(jsonContent).hasJsonPathStringValue("@.curr");
	}

	@Test
	void deserializesFromJson() throws IOException {
		ObjectContent<Prices> jsonContent = json.parse(JSON_PRICE);

		assertThat(jsonContent).isNotNull();
		assertThat(jsonContent).isEqualTo(PRICES);

		Prices price = json.parseObject(JSON_PRICE);
		assertThat(price.getBrandId()).isEqualTo(3);
		assertThat(price.getStartDate()).isEqualTo(testTime);
		assertThat(price.getEndDate()).isEqualTo(testTime);
		assertThat(price.getPriceList()).isEqualTo(1);
		assertThat(price.getProductId()).isEqualTo(35456);
		assertThat(price.getPriority()).isEqualTo(1);
		assertThat(price.getPrice()).isEqualTo(BigDecimal.valueOf(38.95));
	}
}
