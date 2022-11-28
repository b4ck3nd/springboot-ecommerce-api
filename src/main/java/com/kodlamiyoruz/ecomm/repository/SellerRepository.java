package com.kodlamiyoruz.ecomm.repository;

import com.kodlamiyoruz.ecomm.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SellerRepository extends JpaRepository<Seller,Integer> {
    boolean existsSellerByEmail(String email);

    @Modifying
    @Query("delete from Seller s where s.id=:id")
    void deleteBySellerId(int id);
}
