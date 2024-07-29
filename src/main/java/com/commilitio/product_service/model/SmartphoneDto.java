package com.commilitio.product_service.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@EqualsAndHashCode
@ToString
@SuperBuilder
public class SmartphoneDto extends ProductDto {

    private final String color;
    private final String batteryCapacity;
    private final String brand;
    private final String screenSize;

}
