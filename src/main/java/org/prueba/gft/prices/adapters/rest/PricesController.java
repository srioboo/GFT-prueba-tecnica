package org.prueba.gft.prices.adapters.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.websocket.server.PathParam;
import org.prueba.gft.prices.application.PricesService;
import org.prueba.gft.prices.domain.Prices;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
		@RequestParam(name = "start-date", required = false)
		@Parameter(name = "start-date",
			description = "Start date", example = "2020-06-14-15.00.00")
		String startDate,
		@RequestParam(name = "end-date", required = false)
		@Parameter(name = "end-date",
			description = "End date", example = "2020-06-16-15.00.00")
		String endDate
	) {
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyy-MM-dd-HH.mm.ss", Locale.of("ES", "ES"));
		return ResponseEntity.status(HttpStatus.OK).body(pricesService
			.findByProductIdAndBrandIdAndDate(productId, brandId, LocalDateTime.parse(startDate, formatter)));
	}
}