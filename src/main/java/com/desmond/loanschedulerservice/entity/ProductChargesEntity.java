package com.desmond.loanschedulerservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "PRODUCT_CHARGES")
public class ProductChargesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_CHARGE_ID", unique = true, nullable = false, updatable = false)
    private Long productChargeId;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "CHARGE_VALUE", nullable = false)
    private Double value;

    @Column(name = "CHARGE_TYPE", length = 100, nullable = false)
    private String type;

    @Column(name = "PERCENTAGE_ON", length = 100)
    private String percentageOn;

    @Column(name = "CHARGE_ID")
    private Long chargeId;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID_FK")
    @JsonIgnore
    private ProductEntity product;

    public Long getProductChargeId() {
        return productChargeId;
    }

    public void setProductChargeId(Long productChargeId) {
        this.productChargeId = productChargeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPercentageOn() {
        return percentageOn;
    }

    public void setPercentageOn(String percentageOn) {
        this.percentageOn = percentageOn;
    }

    public Long getChargeId() {
        return chargeId;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
