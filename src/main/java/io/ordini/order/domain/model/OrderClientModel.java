package io.ordini.order.domain.model;

import io.ordini.order.adapter.presenter.CreateOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderClientModel {

    private UUID clientId;
    private String name;
    private OrderClientAddressModel address;

}
