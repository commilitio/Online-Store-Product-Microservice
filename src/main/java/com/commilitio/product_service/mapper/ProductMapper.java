package com.commilitio.product_service.mapper;

import com.commilitio.product_service.model.Product;
import com.commilitio.product_service.model.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductDto> toDtos(List<Product> products);
}
