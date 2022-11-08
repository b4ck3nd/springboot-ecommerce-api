package com.kodlamiyoruz.ecomm.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "PRODUCTS")
//@ApiModel(value = "Product Api Model Documentation",description = "Model")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@ApiModelProperty(value = "unique productId field of product object")
    private int productId;

    //@ApiModelProperty(value = "productName field of product object")
    private String productName;
    //@ApiModelProperty(value = "productBrand field of product object")
    private String productBrand;
    //@ApiModelProperty(value = "productPrice field of product object")
    private double productPrice;

    //@ApiModelProperty(value = "stock field of product object")
    private int stock;

    @Column(updatable = false)
    @CreationTimestamp
    //@ApiModelProperty(value = "createdDate field of product object - unupdatable field ")
    private Date createdDate;


    @ManyToOne
    //@ApiModelProperty(value = "seller field of product object")
    private Seller seller;


    @ManyToOne
    //@ApiModelProperty(value = "category field of product object")
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
