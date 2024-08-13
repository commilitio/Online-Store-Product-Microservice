package com.commilitio.product_service.model.configuration.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ComputerConfigurationDto.class, name = "COMPUTER"),
        @JsonSubTypes.Type(value = SmartphoneConfigurationDto.class, name = "SMARTPHONE")
})
public abstract class ConfigurationDto {

    private String type;
}
