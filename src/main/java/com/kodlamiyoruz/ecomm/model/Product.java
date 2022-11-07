package com.kodlamiyoruz.ecomm.model;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    private String productName;
    private String productBrand;
    private double productPrice;
    private int stock;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createdDate;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private Category category;

    public Product(String productName,String productBrand,double productPrice,int stock){
        this.productName=productName;
        this.productBrand=productBrand;
        this.productPrice=productPrice;
        this.stock=stock;
        this.createdDate=new Date();
    }
    public Product(String productName,String productBrand,double productPrice){
        this.productName=productName;
        this.productBrand=productBrand;
        this.productPrice=productPrice;
        this.stock=1;
        this.createdDate=new Date();

    }
    public Product(String productName,String productBrand){
        this.productName=productName;
        this.productBrand=productBrand;
        this.productPrice=1.0;
        this.stock=1;
        this.createdDate=new Date();

    }
    public Product() {
        this.createdDate=new Date();
    }


}
