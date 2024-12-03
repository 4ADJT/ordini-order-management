package io.ordini.order.openfeign;

import io.ordini.order.domain.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name = "product", url = "http://product/products")
public interface ProductClient {

    @GetMapping("/find-id/{id}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id);

    @PutMapping("/{id}/{quantity}")
    ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @PathVariable int quantity);
}
