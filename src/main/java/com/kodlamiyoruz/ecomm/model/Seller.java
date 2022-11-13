package com.kodlamiyoruz.ecomm.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "SELLERS")
@ApiModel(value = "Seller models",description = "Model")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(name = "unique productId field of product object",allowableValues = "Integer")
    @Column(name = "seller_id")
    private int id;

    @ApiModelProperty(notes = "name field of seller object",allowableValues = "String")
    @Column(name = "seller_name")
    private String name;
    @Column(unique = true,name = "seller_email")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Za-z0-9.-]{2,5}$",message = "invalid email")
    //@ValidEmail
    @NotNull
    @Size(min = 1,message = "invalid email")
    @ApiModelProperty(notes = "email field of seller object",allowableValues = "String")
    private String email;

    @Column(updatable = false,name = "created_date")
    @CreationTimestamp
    @ApiModelProperty(notes = "createdDate field of seller object",allowableValues = "Date")
    private Date createdDate;

    @OneToMany()
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ApiModelProperty(notes = "list of products field of seller object",allowableValues = "List of Products")
    private List<Product> products;

    public Seller(String name,String email) {
        this.name=name;
        this.email=email;
        this.createdDate=new Date();
    }
    public Seller() {
        this.createdDate=new Date();

    }

}