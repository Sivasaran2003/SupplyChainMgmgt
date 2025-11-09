package com.siva.supplyChainMgmt.repositories;

import com.siva.supplyChainMgmt.models.product.Batch;
import com.siva.supplyChainMgmt.models.warehouse.WarehouseBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WarehouseBatchRepository extends JpaRepository<WarehouseBatch, Long> {
    @Query(
            "SELECT wb FROM WarehouseBatch wb " +
                    "WHERE wb.warehouse.id = :warehouseId " +
                    "AND wb.batch IN :batches " +
                    "AND wb.status = 'STOCKED'" // Assumes you use 'STOCKED' for active inventory
    )
    List<WarehouseBatch> findByWarehouseIdAndBatches(@Param("warehouseId") Long warehouseId,
                                                     @Param("batches") List<Batch> batches);

    @Query(
            "SELECT wb FROM WarehouseBatch wb " +
                    "WHERE wb.warehouse.id = :warehouseId " +
                    "AND wb.batch IN :batches " +
                    "AND wb.status = 'SHIPPED'"
    )
    List<WarehouseBatch> findShippedBatches(@Param("warehouseId") Long warehouseId,
                                                      @Param("batches") List<Batch> batches);

    @Query(
            "SELECT wb.batch FROM WarehouseBatch wb " +
                    "WHERE wb.warehouse.id = :warehouseId " +
                    "AND wb.status = 'STOCKED'"
    )
    List<Batch> findBatchesByWarehouseId(@Param("warehouseId") Long warehouseId);
}