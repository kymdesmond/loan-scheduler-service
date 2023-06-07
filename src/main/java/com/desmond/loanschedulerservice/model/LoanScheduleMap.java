package com.desmond.loanschedulerservice.model;

import java.util.List;
import java.util.Map;

public class LoanScheduleMap {
    private Map<String, String> charges;
    private Map<String, List<LoanSchedule>> loanScheduleList;

    public Map<String, String> getCharges() {
        return charges;
    }

    public void setCharges(Map<String, String> charges) {
        this.charges = charges;
    }

    public Map<String, List<LoanSchedule>> getLoanScheduleList() {
        return loanScheduleList;
    }

    public void setLoanScheduleList(Map<String, List<LoanSchedule>> loanScheduleList) {
        this.loanScheduleList = loanScheduleList;
    }
}