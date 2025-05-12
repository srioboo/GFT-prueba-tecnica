package org.prueba.gft.prices.adapters.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@Data
class PricesEntity {

    @Id
    private int productId;   // PRODUCT_ID  varchar(256),

    private int priceList;      // PRICE_LIST  INTEGER,
    private int brandId;     // BRAND_ID    INTEGER,
    private LocalDateTime startDate;      // START_DATE  TIMESTAMP,
    private LocalDateTime endDate;       // END_DATE    TIMESTAMP,
    private int priority;       // PRIORITY    INTEGER,
    private BigDecimal price;   // PRICE       DECIMAL,
    private String curr;       // CURR        VARCHAR(3)

}
