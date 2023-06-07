package com.desmond.loanschedulerservice.model;

import java.util.Date;
public class LoanSchedule {

    private int instalmentNumber;

    private Double principal;
    private Double insallmentAmount;
    private Double interest;
    private Double scheduledPayment;
    private Date dueDate;
    private Double beginningBalance;
    private Double endingBalance;

    public int getInstalmentNumber() {
        return instalmentNumber;
    }

    public void setInstalmentNumber(int instalmentNumber) {
        this.instalmentNumber = instalmentNumber;
    }

    public Double getPrincipal() {
        return principal;
    }

    public void setPrincipal(Double principal) {
        this.principal = principal;
    }

    public Double getInsallmentAmount() {
        return insallmentAmount;
    }

    public void setInsallmentAmount(Double insalmentAmount) {
        this.insallmentAmount = insalmentAmount;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getScheduledPayment() {
        return scheduledPayment;
    }

    public void setScheduledPayment(Double scheduledPayment) {
        this.scheduledPayment = scheduledPayment;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Double getBeginningBalance() {
        return beginningBalance;
    }

    public void setBeginningBalance(Double beginningBalance) {
        this.beginningBalance = beginningBalance;
    }

    public Double getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(Double endingBalance) {
        this.endingBalance = endingBalance;
    }


    @Override
    public String toString() {
        return "LoanSchedule{" +
                "instalmentNumber=" + instalmentNumber +
                ", principal=" + principal +
                ", installmentAmount=" + insallmentAmount +
                ", interest=" + interest +
                ", scheduledPayment=" + scheduledPayment +
                ", dueDate=" + dueDate +
                ", beginningBalance=" + beginningBalance +
                ", endingBalance=" + endingBalance +
                '}';
    }
}
