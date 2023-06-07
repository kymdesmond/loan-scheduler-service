package com.desmond.loanschedulerservice.controller;

import com.desmond.loanschedulerservice.dto.ApiResponseDto;
import com.desmond.loanschedulerservice.model.AuthUserRequest;
import com.desmond.loanschedulerservice.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private  final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> authenticateUser(@RequestBody AuthUserRequest userRequest, HttpServletRequest request) {
        ApiResponseDto apiResponseDto = new ApiResponseDto();

        UserDetails userDetails = inMemoryUserDetailsManager.loadUserByUsername(userRequest.getEmail());
        log.info(userDetails.getUsername());
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                final String jwt = jwtUtil.generateToken(userDetails);
                apiResponseDto.setResponseCode("00");
                apiResponseDto.setResponseMessage("Authentication Successful");
                apiResponseDto.setData(jwt);
                return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
            } else {
                apiResponseDto.setResponseCode("01");
                apiResponseDto.setResponseMessage("Authentication Failed. Bad Credentials");
                return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
            }

        } catch (BadCredentialsException e) {
            apiResponseDto.setResponseCode("01");
            apiResponseDto.setResponseMessage("Authentication Failed. Bad Credentials");
            return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
        }

    }
}
