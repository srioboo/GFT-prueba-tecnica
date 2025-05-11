package org.prueba.gft.prices.adapters.rest;

import org.prueba.gft.prices.application.PricesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PricesController {

    private final PricesService pricesService;

    public PricesController(PricesService pricesService) {
        this.pricesService = pricesService;
    }

    @GetMapping(path="/prices")
    public ResponseEntity<String> getPrices() {
        return ResponseEntity.ok(pricesService.findAll().toString());
    }


}