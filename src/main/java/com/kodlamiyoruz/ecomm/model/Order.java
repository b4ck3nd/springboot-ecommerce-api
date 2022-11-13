package com.kodlamiyoruz.ecomm.model;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String productName;
    private String productBrand;
    private double productPrice;
    private int quantity;

    @OneToOne
    private Address address;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private User user;

    @CreationTimestamp
    private Date createdDate;

    public Order() {
        this.createdDate=new Date();
    }

    public Order(String productName,String productBrand,double productPrice,int quantity) {
        this.productName=productName;
        this.productBrand=productBrand;
        this.productPrice=productPrice;
        this.quantity=quantity;
        this.createdDate=new Date();
    }


}
