package io.ordini.order.domain.enums;

public enum TrackingStageEnum {
    TRANSPORT_TO_RETAILER("A transportadora está indo em direção ao varejista"),
    RETAILER_COLLECTED("A transportadora coletou seu produto"),
    TRANSPORT_TO_CITY("A transportadora está indo em direção à sua cidade"),
    OUT_FOR_DELIVERY("Seu produto está em rota de entrega");

    private final String description;

    TrackingStageEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}