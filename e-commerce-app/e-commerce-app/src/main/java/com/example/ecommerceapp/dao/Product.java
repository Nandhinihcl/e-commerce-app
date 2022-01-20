package com.example.ecommerceapp.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String category;
    private Long price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void copyFromPojo(com.example.ecommerceapp.pojo.Product product){
        this.setCategory(product.getCategory());
        this.setPrice(product.getPrice());
        this.setName(product.getName());
    }

    public com.example.ecommerceapp.pojo.Product getPojo(){
        com.example.ecommerceapp.pojo.Product product = new com.example.ecommerceapp.pojo.Product();
        product.setCategory(this.getCategory());
        product.setPrice(this.getPrice());
        product.setName(this.getName());
        product.setId(this.id);
        return product;
    }

}
