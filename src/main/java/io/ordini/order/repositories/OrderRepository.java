package io.ordini.order.repositories;

import io.ordini.order.domain.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, UUID> {



    @Query("SELECT o FROM OrderModel o JOIN FETCH o.items")
    List<OrderModel> findAllOrdersWithItems();

    @Query("SELECT o FROM OrderModel o JOIN FETCH o.items WHERE o.id = :orderId")
    OrderModel getByIdWithItem(UUID orderId);
}

