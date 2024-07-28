package com.commilitio.product_service.mapper;

import com.commilitio.product_service.model.Smartphone;
import com.commilitio.product_service.model.SmartphoneDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SmartphoneMapper {

    SmartphoneDto toDto(Smartphone smartphone);

    List<SmartphoneDto> toDtos(List<Smartphone> smartphones);
}
