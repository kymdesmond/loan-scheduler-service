package com.desmond.loanschedulerservice.dto;

import com.desmond.loanschedulerservice.entity.BankEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link BankEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BankEntityDto(Long bankId, String name, Date dateCreated,
                            List<ProductEntityDto> products) implements Serializable {
}