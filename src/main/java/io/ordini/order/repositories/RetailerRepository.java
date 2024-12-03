package io.ordini.order.repositories;

import io.ordini.order.domain.model.RetailerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RetailerRepository extends JpaRepository<RetailerModel, UUID> {
}

