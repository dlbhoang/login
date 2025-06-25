package com.vnpt.shopguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vnpt.shopguard.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
