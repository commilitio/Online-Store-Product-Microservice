package com.commilitio.product_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Inheritance(strategy = InheritanceType.JOINED)         // wszystkie klasy dziedziczące przechowywane będą w różnych tabelach
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "NAME")
    private String name;
    @Column(nullable = false, name = "PRICE")
    private BigDecimal price;
    @Column(nullable = false, name = "TYPE")
    private String type;
}
