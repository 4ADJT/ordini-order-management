package io.ordini.order.service;

import io.ordini.order.domain.dto.*;
import io.ordini.order.domain.enums.TrackingStageEnum;
import io.ordini.order.domain.model.OrderModel;
import io.ordini.order.domain.model.OrderItemModel;
import io.ordini.order.controller.exception.OrderException;
import io.ordini.order.domain.model.RetailerModel;
import io.ordini.order.openfeign.CustomerClient;
import io.ordini.order.openfeign.DeliveryClient;
import io.ordini.order.openfeign.ProductClient;
import io.ordini.order.repositories.OrderRepository;
import io.ordini.order.repositories.RetailerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RetailerRepository retailerRepository;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private DeliveryClient deliveryClient;

    public OrderModel createOrder(OrderModel orderModel) {
        // Validar cliente
        CustomerDTO customerDTO = validateCustomer(orderModel.getDocument());

        OrderModel order = new OrderModel();
        order.setDocument(orderModel.getDocument());
        order.setStatus(TrackingStageEnum.TRANSPORT_TO_RETAILER.getDescription());
        order.setCreatedAt(LocalDateTime.now());

        log.debug("Validar produtos");
        // Validar produtos
        for (OrderItemModel item : orderModel.getItems()) {
            ProductDTO productDTO = validateProduct(item);
            validateProductStockAndPrice(item, productDTO);
            updateStockProduct(item);

            item.setProductId(item.getProductId());
            item.setQuantity(item.getQuantity());
            item.setPrice(item.getPrice());

            item.setOrder(order);
            order.getItems().add(item);
        }

        // Validar transportadora
        CarrierDTO carrier = getRandomCarrier();

        // Validar varejista
        RetailerModel retailer = getRandomRetailer();
        log.info("Passou do retailer: {}", retailer);

        // Recupera o endereço do cliente
        AddressDTO customerAddress = customerDTO.getAddress();
        log.info("Passou do custumer: {}", customerAddress);

        // Notificar logística
        notifyDelivery(retailer, customerAddress, carrier);
        log.info("Notificar logística");

        // Salvar pedido
        log.info("Salvando: {}", order.getId());
        log.info("Salvando: {}", order.getItems().size());
        return orderRepository.save(order);
    }

    private CustomerDTO validateCustomer(String document) {
        ResponseEntity<CustomerDTO> response = customerClient.getCustomerById(document);
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new OrderException("Cliente inválido!", HttpStatus.BAD_REQUEST);
        }
        return response.getBody();
    }

    private ProductDTO validateProduct(OrderItemModel item) {
        ResponseEntity<ProductDTO> response = productClient.getProductById(item.getProductId());
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new OrderException("Produto inválido: " + item.getProductId(), HttpStatus.BAD_REQUEST);
        }
        return response.getBody();
    }

    private void validateProductStockAndPrice(OrderItemModel item, ProductDTO productDTO) {
        if (productDTO.getStock() < item.getQuantity()) {
            throw new OrderException("Quantidade de produtos está maior que a quantidade em estoque. " +
                    "\n Produto: " + item.getProductId() +
                    "\n Quantidade em estoque: " + productDTO.getStock() +
                    "\n Quantidade requisitada: " + item.getQuantity(), HttpStatus.BAD_REQUEST);
        }

        BigDecimal formattedProductPrice = productDTO.getPrice().setScale(2, RoundingMode.HALF_UP);
        BigDecimal formattedItemPrice = item.getPrice().setScale(2, RoundingMode.HALF_UP);

        if (!Objects.equals(formattedProductPrice, formattedItemPrice)) {
            throw new OrderException("Preço do item está com valor diferente do produto em estoque. " +
                    "\n Preço em estoque: " + productDTO.getPrice(), HttpStatus.BAD_REQUEST);
        }

    }

    private void updateStockProduct(OrderItemModel item) {
        ResponseEntity<ProductDTO> response = productClient.updateProduct(item.getProductId(), item.getQuantity());
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new OrderException("Erro ao atualizar o stock do produto: " + item.getProductId(), HttpStatus.BAD_REQUEST);
        }
    }

    private CarrierDTO getRandomCarrier() {
        ResponseEntity<List<CarrierDTO>> response = deliveryClient.getAllCarriers();
        if (response.getStatusCode() != HttpStatus.OK || response.getBody().isEmpty()) {
            throw new OrderException("Transportador não encontrado", HttpStatus.BAD_REQUEST);
        }
        Random random = new Random();
        return response.getBody().get(random.nextInt(response.getBody().size()));
    }

    private RetailerModel getRandomRetailer() {
        List<RetailerModel> retailers = retailerRepository.findAll();
        if (retailers.isEmpty()) {
            throw new OrderException("Varejista não encontrado.", HttpStatus.BAD_REQUEST);
        }
        Random random = new Random();
        log.info("Número de varejistas encontrados: {}", retailers.size());
        int randomIndex = random.nextInt(retailers.size());
        log.info("Número de varejistas encontrados: {}", retailers.get(randomIndex));
        return retailers.get(randomIndex);
    }

    private void notifyDelivery(RetailerModel retailer, AddressDTO customerAddress, CarrierDTO carrier) {
        DeliveryRequestDTO deliveryRequest = new DeliveryRequestDTO(
                retailer.getLat(),
                retailer.getLon(),
                customerAddress.getLatitude(),
                customerAddress.getLongitude(),
                TrackingStageEnum.TRANSPORT_TO_RETAILER,
                carrier
        );

        ResponseEntity<DeliveryRequestDTO> logisticResponse = deliveryClient.createDelivery(deliveryRequest);
        if (logisticResponse.getStatusCode() != HttpStatus.OK || logisticResponse.getBody() == null) {
            throw new OrderException("Não foi possível notificar o envio do produto.", HttpStatus.BAD_REQUEST);
        }
    }

    public OrderModel updateStatus(UUID orderId, String status) {
        OrderModel orderModel = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Pedido não encontrado.", HttpStatus.NOT_FOUND));
        orderModel.setStatus(status);
        return orderRepository.save(orderModel);
    }

    public OrderModel getOrderById(UUID orderId) {
        return orderRepository.getByIdWithItem(orderId);
    }

    public List<OrderModel> getAllOrders() {
        return orderRepository.findAllOrdersWithItems();
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }
}
