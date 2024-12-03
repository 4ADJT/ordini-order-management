package io.ordini.order.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "retailer")
public class RetailerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String retailer_name;

    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String lon;

}
