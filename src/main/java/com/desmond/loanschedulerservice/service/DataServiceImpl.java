package com.desmond.loanschedulerservice.service;

import com.desmond.loanschedulerservice.entity.BankEntity;
import com.desmond.loanschedulerservice.entity.ProductChargesEntity;
import com.desmond.loanschedulerservice.repository.BankEntityRepository;
import com.desmond.loanschedulerservice.repository.ProductChargesEntityRepository;
import com.desmond.loanschedulerservice.repository.ProductEntityRepository;
import com.desmond.loanschedulerservice.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Data
@Service
@Slf4j
public class DataServiceImpl implements DataService {

    private final BankEntityRepository bankEntityRepository;

    private final ProductEntityRepository productEntityRepository;

    private final ProductChargesEntityRepository productChargesEntityRepository;

    private final UserEntityRepository userEntityRepository;

    @Override
    public List<BankEntity> listAllBanks() {
        return bankEntityRepository.findAll();
    }

    public Optional<ProductChargesEntity> getProductChargesById(Long id) {
        return productChargesEntityRepository.findById(id);
    }
}
