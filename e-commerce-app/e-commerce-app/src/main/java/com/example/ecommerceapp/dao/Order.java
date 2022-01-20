package com.example.ecommerceapp.dao;

import com.example.ecommerceapp.pojo.Orders;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    private String product_name;
    private String product_id;
    private String quantity;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    private Integer customerid;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Orders getPojo(){
        Orders order = new Orders();
        order.setOrderDate(this.getOrderDate());
        order.setProduct_id(this.getProduct_id());
        order.setProduct_name(this.getProduct_name());
        order.setQuantity(this.getQuantity());
        order.setCustomerid(this.getCustomerid());
        return order;
    }


}
