package com.volvo.vcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandlingAdvice {

    @ExceptionHandler(CongestionTaxException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CongestionTaxExceptionResponse> handleCustomException(CongestionTaxException ex) {
        CongestionTaxExceptionResponse response = new CongestionTaxExceptionResponse(LocalDateTime.now(), ex.getMessage(), "Exception Occur in CongestionTax ");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
