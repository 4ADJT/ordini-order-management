package io.ordini.order.service;

import io.ordini.order.domain.dto.AddressDTO;
import io.ordini.order.domain.dto.CustomerDTO;
import io.ordini.order.domain.dto.DeliveryRequestDTO;
import io.ordini.order.domain.dto.ProductDTO;
import io.ordini.order.domain.model.Order;
import io.ordini.order.domain.model.OrderItem;
import io.ordini.order.gateway.CustomerClient;
import io.ordini.order.gateway.LogisticsClient;
import io.ordini.order.gateway.ProductClient;
import io.ordini.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private LogisticsClient logisticsClient;

    public Order createOrder(Order order) {
        // Validar cliente
        ResponseEntity<CustomerDTO> customerResponse = customerClient.getCustomerById(order.getCustomerId());
        if (customerResponse.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Cliente inválido!");
        }

        // Validar produtos
        for (OrderItem item : order.getItems()) {
            ResponseEntity<ProductDTO> productResponse = productClient.getProductById(item.getProductId());
            if (productResponse.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("Produto inválido: " + item.getProductId());
            } else {

                if (productResponse.getBody().getProductQuantity() > item.getQuantity()) {
                    throw new RuntimeException("Quantidade de produtos está maior que a quantidade em estoque. " +
                            "\n Produto: " + item.getProductId() +
                            "\n Quantidade em estoque: " + productResponse.getBody().getProductQuantity() +
                            "\n Quantidade requisitada: " + item.getQuantity()
                    );
                }

                if (productResponse.getBody().getProductPrice() != item.getPrice()) {
                    throw new RuntimeException("Preço do item está com valor diferente do produto em estoque. " +
                            "\n Preço em estoque: " + productResponse.getBody().getProductPrice());
                }

            }
        }

        // Salvar pedido
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("Pedido sendo preparado.");
        Order savedOrder = orderRepository.save(order);

        // Recupera o endereço do cliente
        AddressDTO address = customerResponse.getBody().getAddress();

        // Notificar logística
        DeliveryRequestDTO deliveryRequest = new DeliveryRequestDTO(savedOrder.getId(), savedOrder.getCustomerId(), address.getLatitude(), address.getLongitude());
        logisticsClient.scheduleDelivery(deliveryRequest);

        return savedOrder;
    }

    public Order updateStatus(UUID orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
