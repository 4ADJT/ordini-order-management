package io.ordini.order.adapter.presenter;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CreateOrder {

    @Builder
    public record CreateOrderRequest(
        OrderClient client,
        List<OrderProduct> product
    ) {}

    @Builder
    public record CreateOrderResponse(
            UUID orderId,
            OrderClient client,
            List<OrderProduct> product,
            String status,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record OrderClient(
        UUID clientId,
        String name,
        OrderClientAddress address
    ) {}

    @Builder
    public record OrderClientAddress(
        String street,
        int number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String country,
        String zipCode,
        double longitude,
        double latitude
    ) {}

    @Builder
    public record OrderProduct(
        UUID productId,
        String productName,
        int quantity,
        double price
    ) {}

}
