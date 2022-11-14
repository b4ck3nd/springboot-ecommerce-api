package com.kodlamiyoruz.ecomm.model;

import com.kodlamiyoruz.ecomm.annotation.CardNumberExpiration;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "CREDIT_CARD")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @CardNumberExpiration
    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "ccv")
    private int ccv;

    @Column(name = "expiration_month")
    private int expirationMonth;

    @Column(name = "expiration_year")
    private int expirationYear;

    @Column(name = "name_and_surname")
    private String nameAndSurname;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createdDate;

    @ManyToOne
    private User user;

    public CreditCard() {
        this.createdDate=new Date();
    }
    public CreditCard(String cardNumber,int ccv,int expirationMonth,int expirationYear,String nameAndSurname) {
        this.createdDate=new Date();
        this.nameAndSurname=nameAndSurname;
        this.cardNumber=cardNumber;
        this.ccv=ccv;
        this.expirationMonth=expirationMonth;
        this.expirationYear=expirationYear;
    }


}
