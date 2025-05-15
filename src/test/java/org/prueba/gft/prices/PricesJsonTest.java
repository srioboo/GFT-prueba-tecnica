package org.prueba.gft.prices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prueba.gft.prices.domain.Prices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class PricesJsonTest {

	private static final String JSON_PATH = "/json/prices.json";
	private Prices price;
	private ObjectContent<Prices> jsonContent;

	@Autowired
	private JacksonTester<Prices> json;

	@BeforeEach
	void setUp() throws IOException {
		String testTime = "2020-06-16-15.00.00";
		price = new Prices(3, testTime,
			testTime, 1, 35456, 1, BigDecimal.valueOf(38.95), "EUR");
		jsonContent = json.read(getClass().getResourceAsStream(JSON_PATH));
	}

	@Test
	void serializesToJson() throws IOException {
		JsonContent<Prices> jsonPricesContent = json.write(price);

		assertThat(jsonPricesContent).hasJsonPathNumberValue("@.brandId");
		assertThat(jsonPricesContent).hasJsonPathStringValue("@.startDate");
		assertThat(jsonPricesContent).hasJsonPathStringValue("@.endDate");
		assertThat(jsonPricesContent).hasJsonPathNumberValue("@.priceList");
		assertThat(jsonPricesContent).hasJsonPathNumberValue("@.productId");
		assertThat(jsonPricesContent).hasJsonPathNumberValue("@.priority");
		assertThat(jsonPricesContent).hasJsonPathNumberValue("@.price");
		assertThat(jsonPricesContent).hasJsonPathStringValue("@.curr");
	}

	@Test
	void deserializesFromJson() {
		Prices jsonPrice = jsonContent.getObject();
		assertThat(jsonPrice).isNotNull();

		assertThat(price.getBrandId()).isEqualTo(jsonPrice.getBrandId());
		assertThat(price.getStartDate()).isEqualTo(jsonPrice.getStartDate());
		assertThat(price.getEndDate()).isEqualTo(jsonPrice.getEndDate());
		assertThat(price.getPriceList()).isEqualTo(jsonPrice.getPriceList());
		assertThat(price.getProductId()).isEqualTo(jsonPrice.getProductId());
		assertThat(price.getPriority()).isEqualTo(jsonPrice.getPriority());
		assertThat(price.getPrice()).isEqualTo(jsonPrice.getPrice());
		assertThat(price.getCurr()).isEqualTo(jsonPrice.getCurr());
	}
}
