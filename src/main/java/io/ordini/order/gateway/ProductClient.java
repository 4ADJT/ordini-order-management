package io.ordini.order.gateway;

import io.ordini.order.domain.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "product-service", url = "http://product-service/api/products")
public interface ProductClient {
    @GetMapping("/{id}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id);

//    @PostMapping("/check-stock")
//    ResponseEntity<Boolean> checkStock(@RequestBody StockRequestDTO stockRequest);
}
