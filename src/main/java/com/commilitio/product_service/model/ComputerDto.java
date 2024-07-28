package com.commilitio.product_service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComputerDto extends ProductDto {

    private final String processor;
    private final String ram;
    private final String graphicsCard;
    private final String operatingSystem;
    private final String discCapacity;
}
