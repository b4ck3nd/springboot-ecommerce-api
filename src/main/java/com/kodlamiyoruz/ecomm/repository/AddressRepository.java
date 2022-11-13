package com.kodlamiyoruz.ecomm.repository;


import com.kodlamiyoruz.ecomm.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {

}
