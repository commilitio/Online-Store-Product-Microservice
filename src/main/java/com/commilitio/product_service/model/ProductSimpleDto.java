package com.commilitio.product_service.model;

import com.commilitio.product_service.model.configuration.dto.ConfigurationDto;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductSimpleDto {

    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final ProductType productType;
}
