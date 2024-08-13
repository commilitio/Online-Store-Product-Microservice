package com.commilitio.product_service.model.configuration.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@SuperBuilder
public class ComputerConfigurationDto extends ConfigurationDto {

    private String processor;
    private Integer ram;
    private String graphicsCard;

}
