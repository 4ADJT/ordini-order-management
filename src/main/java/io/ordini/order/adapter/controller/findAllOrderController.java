package io.ordini.order.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Tag(name = "Get order management")
@RestController
@RequestMapping("/order")
public class findAllOrderController {

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "Get all order", description = "Get all order.")
    public ResponseEntity<String> findAllOrder() {

        return ResponseEntity.status(201).body(HttpStatus.OK.toString());
    }

}
