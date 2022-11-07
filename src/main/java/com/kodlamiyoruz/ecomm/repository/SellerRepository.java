package com.kodlamiyoruz.ecomm.repository;

import com.kodlamiyoruz.ecomm.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Integer> {
    boolean existsSellerByEmail(String email);
    Seller findBySellerId(int id);
}
