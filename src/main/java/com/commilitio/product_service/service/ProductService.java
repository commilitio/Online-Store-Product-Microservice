package com.commilitio.product_service.service;

import com.commilitio.product_service.mapper.ProductMapper;
import com.commilitio.product_service.model.Product;
import com.commilitio.product_service.model.ProductDto;
import com.commilitio.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return productMapper.toDtos(products.toList());
    }
}
