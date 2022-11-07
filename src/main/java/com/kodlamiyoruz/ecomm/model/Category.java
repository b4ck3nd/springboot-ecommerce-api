package com.kodlamiyoruz.ecomm.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name =  "CATEGORIES")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;

    @Column(nullable = false)
    private String categoryName;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createdDate;
    @OneToMany
    private List<Product> products;

    public Category(String categoryName) {
        this.categoryName=categoryName;
        this.createdDate=new Date();
    }
    public Category() {
        this.createdDate=new Date();
    }
}
