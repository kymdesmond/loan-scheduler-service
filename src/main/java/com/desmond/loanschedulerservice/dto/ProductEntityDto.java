package com.desmond.loanschedulerservice.dto;

import com.desmond.loanschedulerservice.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link ProductEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductEntityDto(Long productId, String name, Double fixedRate, Double reducingBalance, Date dateCreated,
                               List<ProductChargesEntityDto> productCharges) implements Serializable {
}