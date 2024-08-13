package com.commilitio.product_service.mapper;

import com.commilitio.product_service.model.configuration.*;
import com.commilitio.product_service.model.configuration.dto.ComputerConfigurationDto;
import com.commilitio.product_service.model.configuration.dto.ConfigurationDto;
import com.commilitio.product_service.model.configuration.dto.SmartphoneConfigurationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConfigurationMapper {

    @Mapping(target = "type", constant = "SMARTPHONE")
    SmartphoneConfigurationDto toDto(SmartphoneConfiguration smartphoneConfiguration);

    @Mapping(target = "type", constant = "COMPUTER")
    ComputerConfigurationDto toDto(ComputerConfiguration computerConfiguration);

    default ConfigurationDto toDto(Configuration configuration) {
        if (configuration instanceof SmartphoneConfiguration) {
            return toDto((SmartphoneConfiguration) configuration);
        } else if (configuration instanceof ComputerConfiguration) {
            return toDto((ComputerConfiguration) configuration);
        } else {
            return null;
        }
    }
}

