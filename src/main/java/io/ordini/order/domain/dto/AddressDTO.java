package io.ordini.order.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class AddressDTO {
    private UUID id;
    private String clientId;
    private String street;
    private int number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String longitude;
    private String latitude;
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
