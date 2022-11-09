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
@ApiModel(value = "Product models",description = "Model")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "unique productId field of product object",allowableValues = "Integer")
    @Column(name = "product_id")
    private int productId;

    @ApiModelProperty(notes = "productName field of product model",allowableValues = "String")
    @Column(name = "product_name")
    private String productName;
    @ApiModelProperty(notes = "productBrand field of product model",allowableValues = "String")
    @Column(name = "product_brand")
    private String productBrand;
    @ApiModelProperty(notes = "productPrice field of product model",allowableValues = "Double")
    @Column(name = "product_price")
    private double productPrice;

    @ApiModelProperty(notes = "stock field of product model",allowableValues = "Integer")
    @Column(name = "stock")
    private int stock;

    @Column(updatable = false,name = "created_date")
    @CreationTimestamp
    @ApiModelProperty(notes = "createdDate field of product model",allowableValues = "Date")
    private Date createdDate;


    @ManyToOne
    @ApiModelProperty(notes = "seller field of product model",allowableValues = "Seller")
    private Seller seller;


    @ManyToOne
    @ApiModelProperty(notes = "category field of product model",allowableValues = "Seller")
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
