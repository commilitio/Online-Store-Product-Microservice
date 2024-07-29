package com.commilitio.product_service.controller;

import com.commilitio.product_service.model.ProductDto;
import com.commilitio.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts(Pageable pageable) {
        log.info("getProducts method called with data: {}", pageable);
        return productService.getProducts(pageable);
    }
}
