package com.commilitio.product_service.exception.handler;

import com.commilitio.product_service.exception.ProductNotFoundException;
import com.commilitio.product_service.model.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<MessageDto> handleDoctorException(ProductNotFoundException e) {
        log.error("Encountered error with message: {} and stack trace: {}", e.getMessage(), e.getStackTrace());
        return ResponseEntity.status(e.getHttpStatus()).body(new MessageDto(e.getMessage(), LocalDate.now(), e.getHttpStatus()));
    }
}
