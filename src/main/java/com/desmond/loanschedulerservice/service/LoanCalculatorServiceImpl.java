package com.desmond.loanschedulerservice.service;

import com.desmond.loanschedulerservice.entity.BankEntity;
import com.desmond.loanschedulerservice.entity.ProductChargesEntity;
import com.desmond.loanschedulerservice.entity.ProductEntity;
import com.desmond.loanschedulerservice.model.LoanSchedule;
import com.desmond.loanschedulerservice.model.LoanScheduleMap;
import com.desmond.loanschedulerservice.model.LoanScheduleRequest;
import com.desmond.loanschedulerservice.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanCalculatorServiceImpl implements LoanCalculatorService {

    private final DataService dataService;
    @Override
    public LoanScheduleMap calculate(LoanScheduleRequest loanScheduleRequest) {
        DecimalFormat df = new DecimalFormat("###.##");
        Map<String, List<LoanSchedule>> loanScheduleListMap = new HashMap<>();
        LoanScheduleMap loanScheduleMap = new LoanScheduleMap();
        List<BankEntity> bankEntities = dataService.listAllBanks();

        if (!bankEntities.isEmpty()) {
            log.info("found {} banks", bankEntities.size());
            log.info("retrieving loan products...");
            List<ProductEntity> productEntities = new ArrayList<>();
            bankEntities.forEach(bankEntity -> productEntities.addAll(bankEntity.getProducts()));

            log.info("{} products retrieved", productEntities.size());

            if (!productEntities.isEmpty()) {
                productEntities.forEach(productEntity ->  {
                    Map<String, String> chargesMap = new HashMap<>();
                    log.info("computing charges...");
                    productEntity.getProductCharges().forEach(productChargesEntity -> {
                        if (productChargesEntity.getType().equalsIgnoreCase("percentage")) {
                                if (productChargesEntity.getPercentageOn().equalsIgnoreCase("principal")) {
                                    chargesMap.put(productChargesEntity.getName(), df.format(productChargesEntity.getValue() / 100 * loanScheduleRequest.amount()));
                                } else if (productChargesEntity.getPercentageOn().equalsIgnoreCase("charge")) {
                                    Optional<ProductChargesEntity> optionalProductChargesEntity =
                                            dataService.getProductChargesById(productChargesEntity.getChargeId());
                                    if (optionalProductChargesEntity.isPresent()) {
                                        ProductChargesEntity c = optionalProductChargesEntity.get();
                                        double value = c.getType().equalsIgnoreCase("percentage") && c.getPercentageOn().equalsIgnoreCase("principal") ?
                                                c.getValue() / 100 * loanScheduleRequest.amount() : c.getValue();
                                        chargesMap.put(productChargesEntity.getName(), df.format(productChargesEntity.getValue() / 100 * value));
                                    }
                                 }
                            }
                        else if (productChargesEntity.getType().equalsIgnoreCase("fixed")) {
                            chargesMap.put(productChargesEntity.getName(), df.format(productChargesEntity.getValue()));
                        }
                    });
                    loanScheduleMap.setCharges(chargesMap);
                    List<LoanSchedule> loanSchedules = new ArrayList<>();
                    log.info("calculating loan schedule: bank [{}], product[{}]", productEntity.getBank().getName(), productEntity.getName());
                    double installment_amount = loanScheduleRequest.amount() / loanScheduleRequest.period();
                    double scheduled_payment;
                    int installment_days = getInstallmentPeriodInDays(loanScheduleRequest.period(), loanScheduleRequest.repaymentFrequency());

                    Timestamp loanDueDate = null;
                    switch (loanScheduleRequest.interestType()) {
                        case "REDUCING_BALANCE" -> {
                            log.info("Product -- {} Scheduling Percentage Reducing balance Method (PMT)", productEntity.getName());
                            double prev_installment_ending_balance = 0d;
                            double r = (productEntity.getReducingBalance() / loanScheduleRequest.period()) / 100;
                            double pv = loanScheduleRequest.amount();
                            int nPer = loanScheduleRequest.period();
                            scheduled_payment = Double.parseDouble(df.format(pmt(r, nPer, pv)));
                            for (int i = 0; i < loanScheduleRequest.period(); i++) {
                                LoanSchedule loanSchedule = new LoanSchedule();
                                loanSchedule.setInstalmentNumber(i + 1);
                                loanSchedule.setPrincipal(loanScheduleRequest.amount());
                                double beginning_balance = loanSchedule.getInstalmentNumber() == 1 ? loanScheduleRequest.amount() : prev_installment_ending_balance;

                                double scheduled_instalment_interest = r * beginning_balance;
                                loanSchedule.setInterest(Double.valueOf(df.format(scheduled_instalment_interest)));
                                loanSchedule.setInsallmentAmount(Double.valueOf(df.format(scheduled_payment - scheduled_instalment_interest)));
                                loanSchedule.setBeginningBalance(Double.valueOf(df.format(beginning_balance)));
                                loanSchedule.setEndingBalance(Double.valueOf(df.format(beginning_balance - loanSchedule.getInsallmentAmount())));
                                loanSchedule.setScheduledPayment(Double.valueOf(df.format(installment_amount + scheduled_instalment_interest)));
                                Timestamp timestamp = (loanSchedule.getInstalmentNumber() == 1 ? new Timestamp(loanScheduleRequest.startDate().getTime()) : loanDueDate);
                                loanSchedule.setDueDate(Utils.AddSecondsToTimestamp(timestamp, installment_days * 60 * 60 * 24 / loanScheduleRequest.period()));
                                prev_installment_ending_balance = loanSchedule.getEndingBalance();
                                loanDueDate = new Timestamp(loanSchedule.getDueDate().getTime());
                                loanSchedules.add(loanSchedule);
                                log.info("prepared schedule -- {}", loanSchedule);
                            }
                        }
                        case "FIXED_RATE" -> {
                            log.info("Product -- {} Scheduling Percentage Flat Rate Method", productEntity.getName());
                            double prev_installment_ending_balance = 0d;
                            for (int i = 0; i < loanScheduleRequest.period(); i++) {
                                LoanSchedule loanSchedule = new LoanSchedule();
                                loanSchedule.setInstalmentNumber(i + 1);
                                loanSchedule.setPrincipal(loanScheduleRequest.amount());
                                double beginning_balance = loanSchedule.getInstalmentNumber() == 1 ? loanScheduleRequest.amount() : prev_installment_ending_balance;
                                double installment_interest = (productEntity.getFixedRate()/ loanScheduleRequest.period()/100 * loanScheduleRequest.amount());
                                scheduled_payment = installment_amount + installment_interest;

                                loanSchedule.setInterest(Double.valueOf(df.format(installment_interest)));
                                loanSchedule.setInsallmentAmount(Double.valueOf(df.format(scheduled_payment - installment_interest)));
                                loanSchedule.setBeginningBalance(Double.valueOf(df.format(beginning_balance)));
                                loanSchedule.setEndingBalance(Double.valueOf(df.format(beginning_balance - loanSchedule.getInsallmentAmount())));
                                loanSchedule.setScheduledPayment(Double.valueOf(df.format(scheduled_payment)));
                                Timestamp timestamp = (loanSchedule.getInstalmentNumber() == 1 ? new Timestamp(loanScheduleRequest.startDate().getTime()) : loanDueDate);
                                loanSchedule.setDueDate(Utils.AddSecondsToTimestamp(timestamp, installment_days * 60 * 60 * 24 / loanScheduleRequest.period()));
                                prev_installment_ending_balance = loanSchedule.getEndingBalance();
                                loanDueDate = new Timestamp(loanSchedule.getDueDate().getTime());
                                loanSchedules.add(loanSchedule);
                                log.info("prepared schedule -- {}", loanSchedule);
                            }

                        }
                    }
                    loanScheduleListMap.put(productEntity.getName(), loanSchedules);
                    loanScheduleMap.setLoanScheduleList(loanScheduleListMap);
                });
            }

        }
        return loanScheduleMap;
    }

    double pmt(double r, int nPer, double pv) { //r interest rate, nPer periods, pv present value(Loan Amount)
        return (pv * r) / (1 - Math.pow(1 + r, -nPer));
    }

    public Integer getInstallmentPeriodInDays(int period, String frequency) {
        int days = 0;
        final int days_in_year = LocalDate.now().lengthOfYear();
        if (frequency.equalsIgnoreCase("daily")) {
            days = period;
        } else if (frequency.equalsIgnoreCase("weekly")) {
            days = period * 7;
        } else if (frequency.equalsIgnoreCase("monthly")) {
            days = period * LocalDate.now().lengthOfMonth();
        } else if (frequency.equalsIgnoreCase("quarterly")) {
            days = period * 90;
        } else if (frequency.equalsIgnoreCase("bi-annually")) {
            days = period * 180;
        } else if (frequency.equalsIgnoreCase("annually")) {
            days = period * days_in_year;
        }
        log.info("no of days in year -- {}", days);
        return days;
    }
}
