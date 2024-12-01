package io.ordini.order.openfeign;

import io.ordini.order.domain.dto.DeliveryRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "logistics", url = "http://logistics/api/logistics")
public interface LogisticsClient {
    @PostMapping("/schedule")
    ResponseEntity<Void> scheduleDelivery(@RequestBody DeliveryRequestDTO deliveryRequest);
}
