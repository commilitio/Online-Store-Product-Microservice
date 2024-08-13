package com.commilitio.product_service.service;

import com.commilitio.product_service.exception.ProductAlreadyExistsException;
import com.commilitio.product_service.exception.ProductNotFoundException;
import com.commilitio.product_service.mapper.ProductMapper;
import com.commilitio.product_service.model.Product;
import com.commilitio.product_service.model.ProductDto;
import com.commilitio.product_service.model.configuration.ComputerConfiguration;
import com.commilitio.product_service.model.configuration.Configuration;
import com.commilitio.product_service.model.configuration.SmartphoneConfiguration;
import com.commilitio.product_service.model.configuration.dto.ComputerConfigurationDto;
import com.commilitio.product_service.model.configuration.dto.SmartphoneConfigurationDto;
import com.commilitio.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id, HttpStatus.NOT_FOUND));
        log.info("Fetched product with id: {}", id);
        return productMapper.toDto(product);
    }

    public List<ProductDto> getProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return productMapper.toDtos(products.toList());
    }

    @Transactional
    public ProductDto addProduct(ProductDto productDto) {
        if (productRepository.existsByName(productDto.getName())) {
            log.info("Product with name {} already exists", productDto.getName());
            throw new ProductAlreadyExistsException("Product with the specified name already exists", HttpStatus.CONFLICT);
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setProductType(productDto.getProductType());

        Configuration configuration = null;

        switch (productDto.getProductType()) {
            case SMARTPHONE -> {
                SmartphoneConfigurationDto smartphoneConfigDto = (SmartphoneConfigurationDto) productDto.getConfiguration();
                SmartphoneConfiguration newSmartphoneConfig = new SmartphoneConfiguration();
                newSmartphoneConfig.setColor(smartphoneConfigDto.getColor());
                newSmartphoneConfig.setBatteryCapacity(smartphoneConfigDto.getBatteryCapacity());
                newSmartphoneConfig.setProduct(product);
                configuration = newSmartphoneConfig;
            }
            case COMPUTER -> {
                ComputerConfigurationDto computerConfigDto = (ComputerConfigurationDto) productDto.getConfiguration();
                ComputerConfiguration newComputerConfig = new ComputerConfiguration();
                newComputerConfig.setProcessor(computerConfigDto.getProcessor());
                newComputerConfig.setRam(computerConfigDto.getRam());
                newComputerConfig.setGraphicsCard(computerConfigDto.getGraphicsCard());
                newComputerConfig.setProduct(product);
                configuration = newComputerConfig;
            }
            case ELECTRONICS -> {
                // bez specyficznej konfiguracji
            }
            default -> throw new IllegalArgumentException("Unknown product type: " + productDto.getProductType());
        }
        product.setConfiguration(configuration);

        Product savedProduct = productRepository.save(product);
        log.info("Added new product with id: {}", savedProduct.getId());
        return productMapper.toDto(savedProduct);
    }


    @Transactional
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id, HttpStatus.NOT_FOUND));

        productToUpdate.setName(productDto.getName());
        productToUpdate.setPrice(productDto.getPrice());
        productToUpdate.setProductType(productDto.getProductType());

        Configuration configuration = null;

        switch (productDto.getProductType()) {
            case SMARTPHONE -> {
                SmartphoneConfigurationDto smartphoneConfigDto = (SmartphoneConfigurationDto) productDto.getConfiguration();
                SmartphoneConfiguration existingSmartphoneConfig = (SmartphoneConfiguration) productToUpdate.getConfiguration();
                if (existingSmartphoneConfig == null) {
                    existingSmartphoneConfig = new SmartphoneConfiguration();
                    existingSmartphoneConfig.setProduct(productToUpdate);
                }
                existingSmartphoneConfig.setColor(smartphoneConfigDto.getColor());
                existingSmartphoneConfig.setBatteryCapacity(smartphoneConfigDto.getBatteryCapacity());
                configuration = existingSmartphoneConfig;
            }
            case COMPUTER -> {
                ComputerConfigurationDto computerConfigDto = (ComputerConfigurationDto) productDto.getConfiguration();
                ComputerConfiguration existingComputerConfig = (ComputerConfiguration) productToUpdate.getConfiguration();
                if (existingComputerConfig == null) {
                    existingComputerConfig = new ComputerConfiguration();
                    existingComputerConfig.setProduct(productToUpdate);
                }
                existingComputerConfig.setProcessor(computerConfigDto.getProcessor());
                existingComputerConfig.setRam(computerConfigDto.getRam());
                existingComputerConfig.setGraphicsCard(computerConfigDto.getGraphicsCard());
                configuration = existingComputerConfig;
            }
            case ELECTRONICS -> {
                // brak specyficznej konfiguracji
            }
            default -> throw new IllegalArgumentException("Unknown product type: " + productDto.getProductType());
        }
        productToUpdate.setConfiguration(configuration);

        productRepository.save(productToUpdate);
        log.info("Updated product with id: {}", productToUpdate.getId());
        return productMapper.toDto(productToUpdate);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id, HttpStatus.NOT_FOUND));
        productRepository.delete(product);
        log.info("Deleted product with id: {}", id);
    }

    @Transactional
    public void assignAccessoryToProduct(Long productId, Long accessoryId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId, HttpStatus.NOT_FOUND));
        Product accessory = productRepository.findById(accessoryId)
                .orElseThrow(() -> new ProductNotFoundException("Accessory not found with id: " + accessoryId, HttpStatus.NOT_FOUND));

        if (product.getAccessories().contains(accessory)) {
            log.info("Accessory with id {} is already assigned to product with id {}", accessoryId, productId);
            throw new IllegalArgumentException("Accessory is already assigned to this product");
        }

        product.getAccessories().add(accessory);
        accessory.getAssociatedProducts().add(product);

        productRepository.save(product);
        productRepository.save(accessory);

        log.info("Assigned accessory with id {} to product with id {}", accessoryId, productId);
    }
}
