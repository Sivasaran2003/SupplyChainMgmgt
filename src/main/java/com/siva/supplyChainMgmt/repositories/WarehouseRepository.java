package com.siva.supplyChainMgmt.repositories;

import com.siva.supplyChainMgmt.models.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
