package com.desmond.loanschedulerservice.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BANK")
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANK_ID", nullable = false, unique = true, updatable = false)
    private Long bankId;

    @Column(name = "NAME", length = 100, nullable = false, updatable = true)
    private String name;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @OneToMany(mappedBy = "bank")
    private List<ProductEntity> products;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
