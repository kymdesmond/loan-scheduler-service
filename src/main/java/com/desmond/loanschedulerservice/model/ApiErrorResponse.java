package com.desmond.loanschedulerservice.model;

import lombok.Data;

@Data
public class ApiErrorResponse {

    private String errorCode;
    private String errorMessage;
}
