package io.ordini.order.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductDTO {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String currency;
    private String sourceFile;
    private String createdAt;
    private String updatedAt;

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

    public Integer getStock() {
        return stock;
    }

    public String getCurrency() {
        return currency;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
