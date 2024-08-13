package com.commilitio.product_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class ProductNotFoundException extends ProductServiceException {

    public ProductNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
