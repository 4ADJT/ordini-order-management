package io.ordini.order.domain.dto;

public class ProductDTO {
    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;

    public ProductDTO(int productId, String productName, String productDescription, double productPrice, int productQuantity) {}

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }
}
