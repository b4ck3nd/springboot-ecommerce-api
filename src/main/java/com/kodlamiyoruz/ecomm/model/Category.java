package com.kodlamiyoruz.ecomm.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name =  "CATEGORIES")
@ApiModel(value ="Category models",description = "Model")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "unique identifier field of category model",allowableValues = "Integer")
    @Column(name = "category_id")
    private int categoryId;

    @Column(nullable = false,name = "category_name")
    @ApiModelProperty(notes = "categoryname field of category model",allowableValues ="String" )
    private String categoryName;

    @Column(updatable = false,name = "created_date")
    @CreationTimestamp
    @ApiModelProperty(notes = "createdDate  field of  category model",allowableValues = "Date")
    private Date createdDate;
    @OneToMany
    @ApiModelProperty(notes = "products of category field of  category model",allowableValues = "List of Products")
    private List<Product> products;

    public Category(String categoryName) {
        this.categoryName=categoryName;
        this.createdDate=new Date();
    }
    public Category() {
        this.createdDate=new Date();
    }
}
