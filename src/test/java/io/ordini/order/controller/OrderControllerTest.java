package io.ordini.order.controller;

import io.ordini.order.domain.enums.TrackingStageEnum;
import io.ordini.order.domain.model.OrderModel;
import io.ordini.order.domain.model.OrderItemModel;
import io.ordini.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private OrderModel sampleOrder;

    @BeforeEach
    void setup() {
        sampleOrder = new OrderModel();
        sampleOrder.setId(UUID.randomUUID());
        sampleOrder.setDocument("12345678900");
        sampleOrder.setCreatedAt(LocalDateTime.now());
        sampleOrder.setStatus(TrackingStageEnum.TRANSPORT_TO_RETAILER.getDescription());

        OrderItemModel item = new OrderItemModel();
        item.setProductId(UUID.randomUUID());
        item.setQuantity(2);
        item.setPrice(BigDecimal.valueOf(100.0));

        sampleOrder.setItems(List.of(item));
    }

    @Test
    void testCreateOrder() throws Exception {
        Mockito.when(orderService.createOrder(any(OrderModel.class))).thenReturn(sampleOrder);

        String orderJson = """
                {
                    "document": "12345678900",
                    "items": [
                        {
                            "productId": "c1234a67-8b90-4c9d-b20d-d560f173d9f0",
                            "quantity": 2,
                            "price": 100.0
                        }
                    ]
                }
                """;

        mockMvc.perform(post("/order/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(sampleOrder.getId().toString()))
                .andExpect(jsonPath("$.status").value(sampleOrder.getStatus()));
    }

    @Test
    void testUpdateOrderStatus() throws Exception {
        String newStatus = TrackingStageEnum.TRANSPORT_TO_RETAILER.getDescription();
        sampleOrder.setStatus(newStatus);
        Mockito.when(orderService.updateStatus(eq(sampleOrder.getId()), eq(newStatus))).thenReturn(sampleOrder);

        mockMvc.perform(put("/order/{id}/status", sampleOrder.getId())
                        .param("status", newStatus))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sampleOrder.getId().toString()))
                .andExpect(jsonPath("$.status").value(newStatus));
    }

    @Test
    void testGetOrderById() throws Exception {
        Mockito.when(orderService.getOrderById(sampleOrder.getId())).thenReturn(sampleOrder);

        mockMvc.perform(get("/order/{id}", sampleOrder.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sampleOrder.getId().toString()))
                .andExpect(jsonPath("$.status").value(sampleOrder.getStatus()));
    }

    @Test
    void testGetAllOrders() throws Exception {
        Mockito.when(orderService.getAllOrders()).thenReturn(List.of(sampleOrder));

        mockMvc.perform(get("/order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(sampleOrder.getId().toString()))
                .andExpect(jsonPath("$[0].status").value(sampleOrder.getStatus()));
    }
}
