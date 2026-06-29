package com.fglannaweb.ecomercefglanna.repositories;

import com.fglannaweb.ecomercefglanna.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
