package com.example.bankapplication.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TransactionRes extends Transaction{
    private Long transactionNumber;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date transactionDate;

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Long transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

}
