package com.commilitio.product_service.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class MessageDto {

    private final String message;
    private final LocalDate date;
    private final HttpStatus httpStatus;
}