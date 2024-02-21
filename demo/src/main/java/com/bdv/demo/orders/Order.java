package com.bdv.demo.orders;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;


import com.bdv.demo.beats.Beat;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    @Email
    private String customerEmail;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private Double totalCost;

    @ManyToMany
    private List<Beat> beats;


    public Order() {
    }

    public Long getOrderId() {
        return orderId;
    }


    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    public String getCustomerEmail() {
        return customerEmail;
    }


    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }


    public LocalDateTime getOrderDate() {
        return orderDate;
    }


    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }


    public Double getTotalCost() {
        return totalCost;
    }


    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }


    public List<Beat> getBeats() {
        return beats;
    }


    public void setBeats(List<Beat> beats) {
        this.beats = beats;
    }



}
