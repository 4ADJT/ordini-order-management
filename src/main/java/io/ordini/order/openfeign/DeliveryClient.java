package io.ordini.order.openfeign;

import io.ordini.order.domain.dto.CarrierDTO;
import io.ordini.order.domain.dto.DeliveryRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "delivery", url = "http://host.docker.internal:9090")
public interface DeliveryClient {

    @PostMapping("/deliveries")
    ResponseEntity<DeliveryRequestDTO> createDelivery(@RequestBody DeliveryRequestDTO deliveryRequest);

    @GetMapping("/carriers")
    ResponseEntity<List<CarrierDTO>> getAllCarriers();
}
