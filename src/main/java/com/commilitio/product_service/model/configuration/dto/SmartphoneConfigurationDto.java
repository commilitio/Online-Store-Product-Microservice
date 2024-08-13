package com.commilitio.product_service.model.configuration.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@SuperBuilder
public class SmartphoneConfigurationDto extends ConfigurationDto {

    private String color;
    private String batteryCapacity;

}
