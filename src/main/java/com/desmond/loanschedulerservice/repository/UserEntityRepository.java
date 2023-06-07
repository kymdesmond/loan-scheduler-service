package com.desmond.loanschedulerservice.repository;

import com.desmond.loanschedulerservice.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UsersEntity, Long> {
}