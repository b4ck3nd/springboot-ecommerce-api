package com.kodlamiyoruz.ecomm.repository;


import com.kodlamiyoruz.ecomm.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Integer> {
}
