package io.ordini.order.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerDTO {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String cellphone;
    private String document;
    private AddressDTO address;
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getDocument() {
        return document;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
