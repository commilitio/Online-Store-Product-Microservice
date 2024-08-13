package com.commilitio.product_service.controller;

import com.commilitio.product_service.model.ProductDto;
import com.commilitio.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        log.info("getProduct method called with id: {}", id);
        return productService.getProduct(id);
    }

    @GetMapping
    public List<ProductDto> getProducts(Pageable pageable) {
        log.info("getProducts method called with data: {}", pageable);
        return productService.getProducts(pageable);
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        log.info("addProduct method called with data: {}", productDto);
        return productService.addProduct(productDto);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        log.info("updateProduct method called with id: {}, and data: {}", id, productDto);
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        log.info("deleteProduct method called with id: {}", id);
        productService.deleteProduct(id);
    }

    @PostMapping("/{productId}/accessories/{accessoryId}")
    public void assignAccessoryToProduct(@PathVariable Long productId, @PathVariable Long accessoryId) {
        log.info("assignAccessoryToProduct method called with productId: {}, and accessoryId: {}", productId, accessoryId);
        productService.assignAccessoryToProduct(productId, accessoryId);
    }
}
