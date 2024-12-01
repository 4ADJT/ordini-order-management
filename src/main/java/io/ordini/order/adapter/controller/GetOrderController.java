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
public class GetOrderController {

    @GetMapping(value = "", produces = "application/json")
    @Operation(summary = "Get order", description = "Get order by id.")
    public ResponseEntity<String> getOrder() {

        return ResponseEntity.status(201).body(HttpStatus.OK.toString());
    }

}
