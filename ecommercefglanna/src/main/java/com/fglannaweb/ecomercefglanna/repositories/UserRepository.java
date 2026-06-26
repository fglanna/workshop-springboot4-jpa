package com.fglannaweb.ecomercefglanna.repositories;

import com.fglannaweb.ecomercefglanna.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
