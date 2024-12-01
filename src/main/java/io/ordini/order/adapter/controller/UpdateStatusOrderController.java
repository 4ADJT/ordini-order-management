package io.ordini.order.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Tag(name = "Get order management")
@RestController
@RequestMapping("/order/status")
public class UpdateStatusOrderController {

    @PutMapping(value = "/update", produces = "application/json")
    @Operation(summary = "Update status order", description = "Update status order.")
    public ResponseEntity<String> updateStatusOrder() {

        return ResponseEntity.status(201).body(HttpStatus.OK.toString());
    }

}
