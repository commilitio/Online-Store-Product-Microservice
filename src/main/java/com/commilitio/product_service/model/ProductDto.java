package com.commilitio.product_service.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {

    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final String type;
}
