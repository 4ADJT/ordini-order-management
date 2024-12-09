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

    public String getRetailerLatitude() {
        return retailerLatitude;
    }

    public void setRetailerLatitude(String retailerLatitude) {
        this.retailerLatitude = retailerLatitude;
    }

    public String getRetailerLongitude() {
        return retailerLongitude;
    }

    public void setRetailerLongitude(String retailerLongitude) {
        this.retailerLongitude = retailerLongitude;
    }

    public String getCustomerLatitude() {
        return customerLatitude;
    }

    public void setCustomerLatitude(String customerLatitude) {
        this.customerLatitude = customerLatitude;
    }

    public String getCustomerLongitude() {
        return customerLongitude;
    }

    public void setCustomerLongitude(String customerLongitude) {
        this.customerLongitude = customerLongitude;
    }

    public TrackingStageEnum getTrackingStage() {
        return trackingStage;
    }

    public void setTrackingStage(TrackingStageEnum trackingStage) {
        this.trackingStage = trackingStage;
    }

    public CarrierDTO getCarrier() {
        return carrier;
    }

    public void setCarrier(CarrierDTO carrier) {
        this.carrier = carrier;
    }
}
