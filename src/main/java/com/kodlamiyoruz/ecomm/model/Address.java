package com.kodlamiyoruz.ecomm.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ADDRESS")
@ApiModel(value = "Address models",description = "Model")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "unique identifier field of address object",allowableValues = "Integer")
    private int id;
    @Column(name = "country")
    @ApiModelProperty(notes = "country field of address object",allowableValues = "String")
    private String country;

    @Column(name = "city")
    @ApiModelProperty(notes = "city field of address object",allowableValues = "String")
    private String city;



    @Column(name = "district")
    @ApiModelProperty(notes = "district field of address object",allowableValues = "String")
    private String district;

    @Column(name = "street")
    @ApiModelProperty(notes = "street field of address object",allowableValues = "String")
    private String street;

    @Column(name = "apartment_number")
    @ApiModelProperty(notes = "apartmentnumber field of address object",allowableValues = "String")
    private String apartmentNumber;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ApiModelProperty(notes = "user field of address object",allowableValues = "User")
    private User user;

    @Column(name = "created_date")
    @CreationTimestamp
    @ApiModelProperty(notes = "createddate field of address object",allowableValues = "Date")
    private Date createdDate;

    public Address() {
        this.createdDate=new Date();
    }
    public Address(String city,String country,String district,String street,String apartmentNumber) {
        this.city=city;
        this.country=country;
        this.district=district;
        this.street=street;
        this.apartmentNumber=apartmentNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", created at= " + createdDate +
                '}';
    }
}