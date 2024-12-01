package io.ordini.order.controller;

import io.ordini.order.domain.model.Order;
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
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
    }

    @PutMapping(value = "/{id}/status", produces = "application/json")
    @Operation(summary = "Update status order", description = "Update status order.")
    public ResponseEntity<Order> updateStatus(@PathVariable UUID id, @RequestParam String status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get order", description = "Get order by id.")
    public ResponseEntity<Order> getOrder(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping(value = "", produces = "application/json")
    @Operation(summary = "Get all order", description = "Get all order.")
    public ResponseEntity<List<Order>> getAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }


}
