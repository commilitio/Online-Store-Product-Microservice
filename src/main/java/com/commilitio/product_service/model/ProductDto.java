package com.commilitio.product_service.model;

import com.commilitio.product_service.model.configuration.dto.ConfigurationDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
public class ProductDto {

    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final ProductType productType;
    private final ConfigurationDto configuration;
    private final Set<ProductSimpleDto> accessories;
    private final Set<ProductSimpleDto> associatedProducts;
}
