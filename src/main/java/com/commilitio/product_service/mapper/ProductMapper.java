package com.commilitio.product_service.mapper;

import com.commilitio.product_service.model.Product;
import com.commilitio.product_service.model.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = ConfigurationMapper.class)
public interface ProductMapper {

    @Mapping(source = "configuration", target = "configuration")
    ProductDto toDto(Product product);

    List<ProductDto> toDtos(List<Product> products);
}
