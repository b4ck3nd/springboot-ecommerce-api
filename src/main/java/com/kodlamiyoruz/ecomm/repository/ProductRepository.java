package com.kodlamiyoruz.ecomm.repository;

import com.kodlamiyoruz.ecomm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductBrandContaining(String productBrand);
    List<Product> findByProductNameContaining(String productName);


}
