package com.example.bankapplication.dao;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;
    private Long accountNumber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    private Double openingBal = 0.0;
    private String firstName;
    private String lastName;
    private Short age;
    private Integer uID;
    private Long phoneNumber;
    private String email;


    private String accountType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Double getOpeningBal() {
        return openingBal;
    }

    public void setOpeningBal(Double openingBal) {
        this.openingBal = openingBal;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public Integer getuID() {
        return uID;
    }

    public void setuID(Integer uID) {
        this.uID = uID;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }


    public com.example.bankapplication.pojo.CustomerRes getPojo(){
        com.example.bankapplication.pojo.CustomerRes customer = new com.example.bankapplication.pojo.CustomerRes();
        customer.setAccountNumber(this.getAccountNumber());
        customer.setAge(this.getAge());
        customer.setEmail(this.getEmail());
        customer.setCreationDate(this.getCreationDate());
        customer.setFirstName(this.getFirstName());
        customer.setLastName(this.getLastName());
        customer.setOpeningBal(this.getOpeningBal());
        customer.setuID(this.getuID());
        customer.setPhoneNumber(this.getPhoneNumber());
        customer.setAccountType(this.getAccountType());
        return customer;
    }
    public void copyFromPojo(com.example.bankapplication.pojo.Customer customer){
        this.setAge(customer.getAge());
        this.setEmail(customer.getEmail());
        this.setFirstName(customer.getFirstName());
        this.setLastName(customer.getLastName());
        this.setOpeningBal(customer.getOpeningBal());
        this.setuID(customer.getuID());
        this.setPhoneNumber(customer.getPhoneNumber());
        this.setAccountType(customer.getAccountType());
    }
}
