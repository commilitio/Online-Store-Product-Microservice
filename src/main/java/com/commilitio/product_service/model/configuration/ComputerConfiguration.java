package com.commilitio.product_service.model.configuration;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMPUTER_CONFIGURATION")
public class ComputerConfiguration extends Configuration {

    @Column(nullable = false, name = "PROCESSOR")
    private String processor;

    @Column(nullable = false, name = "RAM")
    private Integer ram;

    @Column(nullable = false, name = "GRAPHICS_CARD")
    private String graphicsCard;

    @Override
    public String toString() {
        return "ComputerConfiguration{" +
                ", processor='" + processor + '\'' +
                ", ram=" + ram +
                ", graphicsCard='" + graphicsCard + '\'' +
                ", productId=" + (getProduct() != null ? getProduct().getId() : null) +
                '}';
    }

}
