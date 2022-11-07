package com.kodlamiyoruz.ecomm.model;


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
@Table
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sellerId;

    private String name;
    @Column(unique = true)
    @Email
    private String email;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createdDate;

    @OneToMany
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
