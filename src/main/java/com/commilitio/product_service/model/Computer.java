package com.commilitio.product_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "COMPUTER")
public class Computer extends Product {

    @Column(nullable = false, name = "PROCESSOR")
    private String processor;
    @Column(nullable = false, name = "RAM")
    private String ram;
    @Column(nullable = false, name = "GRAPHICS_CARD")
    private String graphicsCard;
    @Column(nullable = false, name = "OPERATING_SYSTEM")
    private String operatingSystem;
    @Column(nullable = false, name = "DISC_CAPACITY")
    private String discCapacity;
}
