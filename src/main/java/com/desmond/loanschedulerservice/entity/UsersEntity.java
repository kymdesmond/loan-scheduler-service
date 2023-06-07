package com.desmond.loanschedulerservice.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "USERS")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true, nullable = false, updatable = false)
    private Long userId;
    @Column(name = "USERNAME", unique = true, length = 100)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
