package com.desmond.loanschedulerservice.service;

import com.desmond.loanschedulerservice.entity.BankEntity;
import com.desmond.loanschedulerservice.entity.ProductChargesEntity;

import java.util.List;
import java.util.Optional;

public interface DataService {

    List<BankEntity> listAllBanks();

    Optional<ProductChargesEntity> getProductChargesById(Long id);
}
