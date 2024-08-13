package com.commilitio.product_service.model;

import com.commilitio.product_service.model.configuration.Configuration;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, name = "NAME")
    private String name;
    @Column(nullable = false, name = "PRICE")
    private BigDecimal price;
    @Column(nullable = false, name = "PRODUCT_TYPE")
    @Enumerated
    private ProductType productType;

    @JsonManagedReference       // zapobiega cyklicznym odwołaniom podczas serializacji; strona, która zostanie uwzględniona podczas serializacji do JSON
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
    private Configuration configuration;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "PRODUCT_ACCESSORIES",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACCESSORY_ID")  // odnosi się również do pola id, ale w kontekście produktu, który jest akcesorium dla innego produktu
    )
    private Set<Product> accessories = new HashSet<>();      // okresla jakie akcesoria są dostępne dla tego produktu

    @ManyToMany(mappedBy = "accessories", cascade = CascadeType.ALL)
    private Set<Product> associatedProducts = new HashSet<>();      // okresla do jakich produktów należy dane akcesorium (czyli ten produkt)

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", productType=" + productType +
                ", configurationId=" + (configuration != null ? configuration.getId() : null) +
                ", accessories=" + (accessories != null ? accessories.stream()
                .map(accessory -> String.format("Accessory{id=%d, name='%s', price=%s}",
                        accessory.getId(),
                        accessory.getName(),
                        accessory.getPrice()))
                .collect(Collectors.toSet()) : "[]") +
                ", associatedProducts=" + (associatedProducts != null ? associatedProducts.stream()
                .map(product -> String.format("AssociatedProduct{id=%d, name='%s', price=%s}",
                        product.getId(),
                        product.getName(),
                        product.getPrice()))
                .collect(Collectors.toSet()) : "[]") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product other = (Product) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
