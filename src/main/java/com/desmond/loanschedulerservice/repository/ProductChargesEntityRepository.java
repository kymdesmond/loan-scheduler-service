package com.desmond.loanschedulerservice.repository;

import com.desmond.loanschedulerservice.entity.ProductChargesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductChargesEntityRepository extends JpaRepository<ProductChargesEntity, Long> {
}