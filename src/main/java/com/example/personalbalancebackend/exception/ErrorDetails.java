package com.example.personalbalancebackend.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ErrorDetails {
    private Date timestamp;
    private String errorMessage;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.errorMessage = message;
        this.details = details;
    }
}
