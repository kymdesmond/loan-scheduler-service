package com.desmond.loanschedulerservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", nullable = false, unique = true, updatable = false)
    private Long productId;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "FIXED_RATE")
    private Double fixedRate;

    @Column(name = "REDUCING_BALANCE")
    private Double reducingBalance;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "BANK_ID_FK")
    @JsonIgnore
    private BankEntity bank;

    @OneToMany(mappedBy = "product")
    private List<ProductChargesEntity> productCharges;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(Double fixedRate) {
        this.fixedRate = fixedRate;
    }

    public Double getReducingBalance() {
        return reducingBalance;
    }

    public void setReducingBalance(Double reducingBalance) {
        this.reducingBalance = reducingBalance;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BankEntity getBank() {
        return bank;
    }

    public void setBank(BankEntity bank) {
        this.bank = bank;
    }

    public List<ProductChargesEntity> getProductCharges() {
        return productCharges;
    }

    public void setProductCharges(List<ProductChargesEntity> productCharges) {
        this.productCharges = productCharges;
    }
}
