package com.commilitio.product_service.mapper;

import com.commilitio.product_service.model.Computer;
import com.commilitio.product_service.model.ComputerDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComputerMapper {

    ComputerDto toDto(Computer computer);

    List<ComputerDto> toDtos(List<Computer> computers);
}
