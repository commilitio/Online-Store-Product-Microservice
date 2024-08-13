package com.commilitio.product_service.model.configuration;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SMARTPHONE_CONFIGURATION")
public class SmartphoneConfiguration extends Configuration {

    @Column(nullable = false, name = "COLOR")
    private String color;

    @Column(nullable = false, name = "BATTERY_CAPACITY")
    private String batteryCapacity;

    @Override
    public String toString() {
        return "SmartphoneConfiguration{" +
                ", color='" + color + '\'' +
                ", batteryCapacity='" + batteryCapacity + '\'' +
                ", productId=" + (getProduct() != null ? getProduct().getId() : null) +
                '}';
    }

}
