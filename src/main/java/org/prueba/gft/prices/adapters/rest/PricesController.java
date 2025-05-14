package org.prueba.gft.prices.adapters.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.prueba.gft.prices.application.PricesService;
import org.prueba.gft.prices.common.DateUtils;
import org.prueba.gft.prices.domain.Prices;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/")
public class PricesController {

	private final PricesService pricesService;

	public PricesController(PricesService pricesService) {
		this.pricesService = pricesService;
	}

	@GetMapping(path = "/prices", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get list of prices",
		description = "Give al prices")
	public ResponseEntity<List<Prices>> getPrices() {
		return ResponseEntity.ok(pricesService.findAll());
	}

	@GetMapping(path = "/prices/brand/{brandId}/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get list of prices given product ",
		description = "Given brand id, product id and date get final price")
	public ResponseEntity<List<Prices>> getPricesByDateProductIdBrandId(
		@PathVariable("brandId")
		@Parameter(name = "brandId",
			description = "Value of the brand identifier", example = "1")
		int brandId,
		@PathVariable("productId")
		@Parameter(name = "productId",
			description = "Value of the product identifier", example = "35455")
		int productId,
		@RequestParam(name = "date", required = false)
		@Parameter(name = "date",
			description = "Date", example = "2020-06-14-15.00.00")
		String date
	) {
		LocalDateTime localDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
		if (date != null)
			localDate = DateUtils.prepareDate(date);
		return ResponseEntity.status(HttpStatus.OK).body(pricesService
			.findByProductIdAndBrandIdByDate(productId, brandId, localDate));
	}
}