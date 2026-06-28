package com.fglannaweb.ecomercefglanna.repositories;

import com.fglannaweb.ecomercefglanna.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
