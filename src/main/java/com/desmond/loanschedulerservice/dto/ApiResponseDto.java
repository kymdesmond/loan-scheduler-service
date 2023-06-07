package com.desmond.loanschedulerservice.dto;

import com.desmond.loanschedulerservice.model.ApiErrorResponse;
import lombok.Data;

@Data
public class ApiResponseDto extends ApiErrorResponse {
    private String responseCode;
    private String responseMessage;
    private String data;
}
