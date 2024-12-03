package io.ordini.order.domain.dto;

import io.ordini.order.domain.enums.TrackingStageEnum;

public class DeliveryRequestDTO {

    private String retailerLatitude;
    private String retailerLongitude;

    private String customerLatitude;
    private String customerLongitude;

    private TrackingStageEnum trackingStage;

    private CarrierDTO carrier;

    public DeliveryRequestDTO(String retailerLatitude, String retailerLongitude, String customerLatitude, String customerLongitude, TrackingStageEnum trackingStage, CarrierDTO carrier) {
        this.retailerLatitude = retailerLatitude;
        this.retailerLongitude = retailerLongitude;
        this.customerLatitude = customerLatitude;
        this.customerLongitude = customerLongitude;
        this.trackingStage = trackingStage;
        this.carrier = carrier;
    }
}
