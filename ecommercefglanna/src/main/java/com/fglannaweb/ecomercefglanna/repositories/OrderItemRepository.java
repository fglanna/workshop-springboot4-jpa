package com.fglannaweb.ecomercefglanna.repositories;

import com.fglannaweb.ecomercefglanna.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
