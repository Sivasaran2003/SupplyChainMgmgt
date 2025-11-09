package com.siva.supplyChainMgmt.repositories;

import com.siva.supplyChainMgmt.models.retailer.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetailerRepository extends JpaRepository<Retailer, Long> {
}
