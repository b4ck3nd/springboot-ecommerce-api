package com.kodlamiyoruz.ecomm.repository;

import com.kodlamiyoruz.ecomm.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {


}
