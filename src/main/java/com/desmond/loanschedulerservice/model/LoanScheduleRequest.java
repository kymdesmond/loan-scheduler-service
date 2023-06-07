package com.desmond.loanschedulerservice.model;

import java.util.Date;

public record LoanScheduleRequest(
        Double amount,
        String repaymentFrequency,
        Integer period,
        Date startDate,
        String interestType) {}
