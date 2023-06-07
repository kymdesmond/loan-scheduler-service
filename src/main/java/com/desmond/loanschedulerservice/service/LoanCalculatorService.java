package com.desmond.loanschedulerservice.service;

import com.desmond.loanschedulerservice.model.LoanScheduleMap;
import com.desmond.loanschedulerservice.model.LoanScheduleRequest;

public interface LoanCalculatorService {
    LoanScheduleMap calculate(LoanScheduleRequest loanScheduleRequest);
}
