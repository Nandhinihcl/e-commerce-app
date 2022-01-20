package com.example.bankapplication.dao;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "transactions")
public class Transaction {

    @Id
    @GeneratedValue
    private Integer id;
    private Long fromAccountNumber;
    private Long toAccountNumber;
    private Long  transactionNumber;
    private Double amount;
    private String comments;
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public Long getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Long transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public com.example.bankapplication.pojo.TransactionRes getPojo(){
        com.example.bankapplication.pojo.TransactionRes transaction = new com.example.bankapplication.pojo.TransactionRes();
        transaction.setTransactionNumber(this.getTransactionNumber());
        transaction.setAmount(this.getAmount());
        transaction.setComments(this.getComments());
        transaction.setFromAccountNumber(this.getFromAccountNumber());
        transaction.setToAccountNumber(this.getToAccountNumber());
        transaction.setTransactionDate(this.getTransactionDate());
        return transaction;
    }

    public void copyFromPojo(com.example.bankapplication.pojo.Transaction transaction){
        this.setAmount(transaction.getAmount());
        this.setComments(transaction.getComments());
        this.setFromAccountNumber(transaction.getFromAccountNumber());
        this.setToAccountNumber(transaction.getToAccountNumber());
    }
}
