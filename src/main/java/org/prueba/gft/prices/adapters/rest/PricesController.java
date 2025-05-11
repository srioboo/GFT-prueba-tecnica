package org.prueba.gft.prices.adapters.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.websocket.server.PathParam;
import org.prueba.gft.prices.application.PricesService;
import org.prueba.gft.prices.domain.Prices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/")
public class PricesController {

    private final PricesService pricesService;

    public PricesController(PricesService pricesService) {
        this.pricesService = pricesService;
    }

    @GetMapping(path="/prices", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get list of prices",
            description = "Give al prices")
    public ResponseEntity<List<Prices>> getPrices() {
        return ResponseEntity.ok(pricesService.findAll());
    }

    @GetMapping(path="/prices/product/{productId}/brand/{brandId}/date/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get list of prices given product ",
                description = "Given brand id, product id and date get final price")
    public ResponseEntity<String> getPricesByDateProductIdBrandId(
            @PathParam("productId")
            @Parameter(name = "productId",
                    description = "Value of the product identifier", example = "35455")
            String productId,
            @PathParam("brandId")
            @Parameter(name = "brandId",
                    description = "Value of the brand identifier", example = "1")
            int brandId,
            @PathParam("date")
            @Parameter(name = "date",
                    description = "Date to search for", example = "2020-06-14-00.00.00")
            LocalDateTime date
    ) {
        pricesService.findByProductIdAndBrandIdAndDate(productId, brandId, date);
        return ResponseEntity.ok(pricesService.findAll().toString());
    }
}