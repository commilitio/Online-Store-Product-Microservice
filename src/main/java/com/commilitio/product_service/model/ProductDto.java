package com.commilitio.product_service.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@ToString
@SuperBuilder
public class ProductDto {

    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final String type;
}
