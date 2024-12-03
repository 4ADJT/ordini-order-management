package io.ordini.order.controller;

import io.ordini.order.domain.model.RetailerModel;
import io.ordini.order.service.RetailerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Tag(name = "Get retailer management")
@RestController
@RequestMapping("/retailer")
public class RetailerController {

    private final RetailerService retailerService;

    @GetMapping
    @Operation(summary = "Get retailer", description = "Get retailer.")
    public ResponseEntity<List<RetailerModel>> getRetailer() {
        return ResponseEntity.ok(retailerService.getRetailer());
    }

    @GetMapping
    @Operation(summary = "Get retailer by id", description = "Get retailer by id.")
    public ResponseEntity<RetailerModel> getRetailerById(@RequestParam UUID id) {
        return ResponseEntity.ok(retailerService.getRetailerById(id));
    }

    @PostMapping
    @Operation(summary = "Create retailer", description = "Create retailer.")
    public ResponseEntity<RetailerModel> createRetailer(@RequestBody RetailerModel retailer) {
        return ResponseEntity.ok(retailerService.createRetailer(retailer));
    }

    @PutMapping
    @Operation(summary = "Update retailer", description = "Update retailer.")
    public ResponseEntity<RetailerModel> updateRetailer(@RequestParam UUID id,@RequestBody RetailerModel retailer) {
        return ResponseEntity.ok(retailerService.updateRetailer(id, retailer));
    }

    @DeleteMapping
    @Operation(summary = "Delte retailer", description = "Delete retailer.")
    public ResponseEntity<HttpStatus> deleteRetailer(@RequestParam UUID id) {
        retailerService.deleteRetailer(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

}
