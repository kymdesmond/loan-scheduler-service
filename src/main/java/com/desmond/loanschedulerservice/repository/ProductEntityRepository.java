package com.desmond.loanschedulerservice.repository;

import com.desmond.loanschedulerservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {
}