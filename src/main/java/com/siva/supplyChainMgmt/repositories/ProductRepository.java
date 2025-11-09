package com.siva.supplyChainMgmt.repositories;

import com.siva.supplyChainMgmt.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
