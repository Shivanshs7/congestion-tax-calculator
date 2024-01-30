package com.volvo.vcc.exception;

import java.time.LocalDateTime;

public class CongestionTaxExceptionResponse {

    private LocalDateTime timestamp;
    private String message;
    private String details;

    public CongestionTaxExceptionResponse(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

}
