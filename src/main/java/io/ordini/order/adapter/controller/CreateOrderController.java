package io.ordini.order.adapter.controller;

import io.ordini.order.adapter.presenter.CreateOrder;
import io.ordini.order.application.CreateOrderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Tag(name = "Create order management")
@RestController
@RequestMapping("/order")
public class CreateOrderController {

    private final CreateOrderUseCase createOrderUseCase;

    @PostMapping(value = "/create", produces = "application/json")
    @Operation(summary = "Create order", description = "Create order.")
    public ResponseEntity<CreateOrder.CreateOrderResponse> createOrder(
            @RequestBody CreateOrder.CreateOrderRequest request
    ) {

        CreateOrder.CreateOrderResponse client = createOrderUseCase.execute(request);

        return ResponseEntity.status(201).body(client);
    }

}