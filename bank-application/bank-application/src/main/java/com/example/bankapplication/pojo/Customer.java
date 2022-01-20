package com.example.bankapplication.pojo;

import java.util.Date;

public class Customer {

    private Double openingBal = 0.0;
    private String firstName;
    private String lastName;
    private Short age;
    private Integer uID;
    private Long phoneNumber;
    private String email;
    private String accountType;


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
}
