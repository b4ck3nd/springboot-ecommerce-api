package com.kodlamiyoruz.ecomm.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    private int sellerId;

    @ApiModelProperty(notes = "name field of seller object",allowableValues = "String")
    private String name;
    @Column(unique = true)
    @Email
    @ApiModelProperty(notes = "email field of seller object",allowableValues = "String")
    private String email;

    @Column(updatable = false)
    @CreationTimestamp
    @ApiModelProperty(notes = "createdDate field of seller object",allowableValues = "Date")
    private Date createdDate;

    @OneToMany
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
