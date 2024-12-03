package io.ordini.order.controller;

import io.ordini.order.domain.model.OrderModel;
import io.ordini.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Tag(name = "Get order management")
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/create", produces = "application/json")
    @Operation(summary = "Create order", description = "Create order.")
    public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel orderModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderModel));
    }

    @PutMapping(value = "/{id}/status", produces = "application/json")
    @Operation(summary = "Update status order", description = "Update status order.")
    public ResponseEntity<OrderModel> updateStatus(@PathVariable UUID id, @RequestParam String status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get order", description = "Get order by id.")
    public ResponseEntity<OrderModel> getOrder(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping(value = "", produces = "application/json")
    @Operation(summary = "Get all order", description = "Get all order.")
    public ResponseEntity<List<OrderModel>> getAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }


}
