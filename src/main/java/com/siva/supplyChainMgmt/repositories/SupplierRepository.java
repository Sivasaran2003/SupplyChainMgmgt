package com.siva.supplyChainMgmt.repositories;

import com.siva.supplyChainMgmt.models.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
