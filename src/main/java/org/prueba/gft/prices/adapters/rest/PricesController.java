package org.prueba.gft.prices.adapters.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.prueba.gft.prices.domain.model.DateFormatIncorrectException;
import org.prueba.gft.prices.domain.model.PriceNotFoundException;
import org.prueba.gft.prices.domain.service.PricesService;
import org.prueba.gft.prices.mapper.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RestControllerAdvice
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PricesController {

	private static final String TIME_STAMP = "timestamp";
	private static final String MESSAGE = "message";
	private static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.of("ES", "ES"));

	private final PricesService pricesService;

	@ExceptionHandler(PriceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handlePriceNotFound(PriceNotFoundException ex) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put(TIME_STAMP, LocalDateTime.now());
		errorResponse.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DateFormatIncorrectException.class)
	public ResponseEntity<Map<String, Object>> handleDateFormatIncorrectFound(DateFormatIncorrectException ex) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put(TIME_STAMP, LocalDateTime.now());
		errorResponse.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Map<String, Object>> handleNoResourceFound(NoResourceFoundException ex) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put(TIME_STAMP, LocalDateTime.now());
		errorResponse.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, Object>> handleArgumentTypeMismatchFound(MethodArgumentTypeMismatchException ex) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put(TIME_STAMP, LocalDateTime.now());
		errorResponse.put(MESSAGE, ex.getMessage());
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
		try {
			if (date != null && !date.isEmpty())
				localDate = LocalDateTime.parse(date, FORMATTER);
		} catch (DateTimeParseException ex) {
			throw new DateFormatIncorrectException();
		}
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(PriceMapper.INSTANCE
				.toDTO(pricesService
					.findByProductIdAndBrandIdByDate(productId, brandId, localDate)));
	}
}