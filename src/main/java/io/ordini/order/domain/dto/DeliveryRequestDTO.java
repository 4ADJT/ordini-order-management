package io.ordini.order.domain.dto;

import java.util.UUID;

public class DeliveryRequestDTO {
    private UUID orderId;
    private UUID customerId;
    private Long latitude;
    private Long longitude;

    public DeliveryRequestDTO(UUID orderId, UUID customerId, Long latitude, Long longitude) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
