package com.commilitio.product_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "SMARTPHONE")
public class Smartphone extends Product {

    @Column(nullable = false, name = "COLOR")
    private String color;
    @Column(nullable = false, name = "BATTERY_CAPACITY")
    private String batteryCapacity;
    @Column(nullable = false, name = "BRAND")
    private String brand;
    @Column(nullable = false, name = "SCREEN_SIZE")
    private String screenSize;
}
