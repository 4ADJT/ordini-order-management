package io.ordini.order.openfeign;

import io.ordini.order.domain.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "clients", url = "http://host.docker.internal:8080/client")
public interface CustomerClient {

    @GetMapping("/find-document/{document}")
    ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String document);
}