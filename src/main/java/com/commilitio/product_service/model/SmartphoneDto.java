package com.commilitio.product_service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SmartphoneDto extends ProductDto {

    private final String color;
    private final String batteryCapacity;
    private final String brand;
    private final String screenSize;
}
