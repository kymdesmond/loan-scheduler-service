package com.desmond.loanschedulerservice.repository;

import com.desmond.loanschedulerservice.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankEntityRepository extends JpaRepository<BankEntity, Long> {
}