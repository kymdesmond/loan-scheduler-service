package com.desmond.loanschedulerservice.model;

import lombok.Data;

@Data
public class AuthUserRequest {

    private String email;
    private String password;
}
