package com.commilitio.product_service.model.configuration;

import com.commilitio.product_service.model.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "CONFIGURATION")
public abstract class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference      // zapobiega cyklicznym odwołaniom podczas serializacji; ta strona zostanie pominięta podczas serializacji do JSON
    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", productId=" + (product != null ? product.getId() : null) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Configuration)) return false;
        Configuration other = (Configuration) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

