package io.ordini.order.gateway;

import io.ordini.order.domain.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "customer-service", url = "http://customer-service/api/customers")
public interface CustomerClient {
    @GetMapping("/{id}")
    ResponseEntity<CustomerDTO> getCustomerById(@PathVariable UUID id);
}