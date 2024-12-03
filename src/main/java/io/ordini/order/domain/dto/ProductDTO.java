package io.ordini.order.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductDTO {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private String currency;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCurrency() {
        return currency;
    }

}
