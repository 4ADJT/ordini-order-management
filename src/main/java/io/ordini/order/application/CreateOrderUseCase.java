package io.ordini.order.application;

import io.ordini.order.adapter.presenter.CreateOrder;
import io.ordini.order.domain.model.OrderClientModel;
import io.ordini.order.domain.model.OrderModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateOrderUseCase {

    public CreateOrder.CreateOrderResponse execute(CreateOrder.CreateOrderRequest orderRequest) {



        OrderModel orderModel = OrderModel
                .builder()
                .client(orderRequest.client())
                .product(orderRequest.product());

        return CreateOrder.CreateOrderResponse.builder()
                .idOrder()
                .client()
                .product()
                .status()
                .createdAt()

    }

}
