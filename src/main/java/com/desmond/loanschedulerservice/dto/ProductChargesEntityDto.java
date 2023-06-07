package com.desmond.loanschedulerservice.dto;

import com.desmond.loanschedulerservice.entity.ProductChargesEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link ProductChargesEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductChargesEntityDto(Long productChargeId, String name, String type, String percentageOn,
                                      Long chargeId, Date dateCreated) implements Serializable {
}