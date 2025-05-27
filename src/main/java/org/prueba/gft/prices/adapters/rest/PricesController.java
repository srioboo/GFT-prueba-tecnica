package org.prueba.gft.prices.adapters.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.prueba.gft.prices.application.DateUtils;
import org.prueba.gft.prices.domain.dto.PricesDTO;
import org.prueba.gft.prices.domain.mapper.PriceMapper;
import org.prueba.gft.prices.domain.model.PriceNotFoundException;
import org.prueba.gft.prices.domain.model.Prices;
import org.prueba.gft.prices.domain.service.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@RestController
@RestControllerAdvice
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PricesController {

	private final PricesService pricesService;
	private final DateUtils dateUtils;

	@ExceptionHandler(PriceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handlePriceNotFound(PriceNotFoundException ex) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("timestamp", LocalDateTime.now());
		errorResponse.put("message", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@GetMapping(path = "/prices/brand/{brandId}/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get list of prices given product ",
		description = "Given brand id, product id and date get final price")
	public ResponseEntity<PricesDTO> getPricesByDateProductIdBrandId(
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
			description = "Date: search for given date o if null for current date", example = "2020-06-14-15.00.00")
		String date
	) throws PriceNotFoundException {
		LocalDateTime localDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
		if (date != null)
			localDate = dateUtils.prepareDate(date);
		Prices price = new Prices();
		try {
			price = pricesService
				.findByProductIdAndBrandIdByDate(productId, brandId, localDate);
		} catch (Exception ex) {
			throw new PriceNotFoundException("Price Not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(PriceMapper.INSTANCE.toDTO(price));
	}
}