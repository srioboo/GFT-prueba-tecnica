package org.prueba.gft.prices.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prices implements Serializable {

    private int brandId;     // BRAND_ID    INTEGER,
    private LocalDateTime starDate;      // START_DATE  TIMESTAMP,
    private LocalDateTime endDate;       // END_DATE    TIMESTAMP,
    private int priceList;      // PRICE_LIST  INTEGER,
    private int productId;   // PRODUCT_ID  varchar(256),
    private int priority;       // PRIORITY    INTEGER,
    private BigDecimal price;   // PRICE       DECIMAL,
    private String curr;       // CURR        VARCHAR(3)
}
