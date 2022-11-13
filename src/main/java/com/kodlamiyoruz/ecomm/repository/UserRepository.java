package com.kodlamiyoruz.ecomm.repository;

import com.kodlamiyoruz.ecomm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
    User findByEmail(String email);
    boolean existsByEmail(String email);
    User findUserNameById(int id);

    User findEmailById(int id);

}
