package com.desmond.loanschedulerservice.controller;

import com.desmond.loanschedulerservice.entity.BankEntity;
import com.desmond.loanschedulerservice.model.LoanScheduleMap;
import com.desmond.loanschedulerservice.model.LoanScheduleRequest;

import com.desmond.loanschedulerservice.service.DataService;
import com.desmond.loanschedulerservice.service.LoanCalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoanCalculatorController {

    private final LoanCalculatorService loanCalculatorService;

    private final DataService dataService;

    @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanScheduleMap> calculateLoanSchedule(@RequestBody LoanScheduleRequest loanDetailsRequest) {
        log.info("received new request -- {}", loanDetailsRequest);
        LoanScheduleMap loanScheduleMap = loanCalculatorService.calculate(loanDetailsRequest);
        return ResponseEntity.ok(loanScheduleMap);
    }

    @GetMapping(value = "/banks")
    public ResponseEntity<List<BankEntity>> getBanks() {
        return ResponseEntity.ok(dataService.listAllBanks());
    }
}
