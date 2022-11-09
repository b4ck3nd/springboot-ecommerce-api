package com.kodlamiyoruz.ecomm.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")
@ApiModel(value = "User models",description = "Model")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "unique identifier field of user object",allowableValues = "Integer")
    private int userId;

    @NotNull(message = "username cannot be empty and be unique")
    @Column(name = "username")
    @ApiModelProperty(notes = "username field of user object",allowableValues = "String")
    private String userName;

    @NotNull
    @Column(name = "password")
    @ApiModelProperty(notes = "password field of user object",allowableValues = "String")
    private String password;
    @NotNull
    @Column(name = "email")
    @Email(message = "this email address has been used before")
    @ApiModelProperty(name = "email field of user object",allowableValues = "String")
    private String email;

    @Column(name = "created_date")
    @CreationTimestamp
    @ApiModelProperty(notes = "createdDate field of user object",allowableValues = "Date")
    private Date createdDate;

    @OneToMany
    @ApiModelProperty(notes = "list of productcomment field of user object",allowableValues = "List of ProductComment")
    private List<ProductComment> productComment;


    @OneToMany
    @ApiModelProperty(notes = "list of address field of user object",allowableValues = "List of Address")
    private List<Address> address;

    public  User() {
        this.createdDate=new Date();
    }
    public User(String userName,String password,String email){
        this.userName=userName;
        this.password=password;
        this.email=email;
        this.createdDate=new Date();
    }

}
