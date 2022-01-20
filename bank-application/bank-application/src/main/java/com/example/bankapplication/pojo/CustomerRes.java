package com.example.bankapplication.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CustomerRes extends Customer{
    private Long accountNumber;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
