package com.siva.supplyChainMgmt.services;

import com.siva.supplyChainMgmt.enums.TxnEnums;
import com.siva.supplyChainMgmt.models.product.Batch;
import com.siva.supplyChainMgmt.models.warehouse.Warehouse;
import com.siva.supplyChainMgmt.models.warehouse.WarehouseBatch;
import org.springframework.stereotype.Service;
import com.siva.supplyChainMgmt.repositories.WarehouseBatchRepository;
import com.siva.supplyChainMgmt.repositories.WarehouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepo;
    private final WarehouseBatchRepository warehouseBatchRepo;

    public WarehouseService(WarehouseRepository warehouseRepo, WarehouseBatchRepository warehouseBatchRepo) {
        this.warehouseRepo = warehouseRepo;
        this.warehouseBatchRepo = warehouseBatchRepo;
    }

    public Warehouse registerWarehouse(Warehouse warehouse) {
        return warehouseRepo.save(warehouse);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepo.findAll();
    }

    public void deleteWarehouse(Long id) {
        warehouseRepo.deleteById(id);
    }

    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepo.findById(id);
    }

    // add a batch to the warehouse
    public void stockBatches(Warehouse warehouse, List<Batch> batches) {
        List<WarehouseBatch> warehouseBatches = new ArrayList<>();
        for(Batch batch : batches) {
            warehouseBatches.add(WarehouseBatch.builder()
                    .batch(batch)
                    .batchStatus(TxnEnums.BatchStatus.STOCKED)
                    .warehouse(warehouse)
                    .build());
        }
        warehouseBatchRepo.saveAll(warehouseBatches);
    }

    public List<WarehouseBatch> findByWarehouseIdAndBatches(Long id, List<Batch> batches) {
        return warehouseBatchRepo.findByWarehouseIdAndBatches(id, batches);
    }

    public void unstockBatches(List<WarehouseBatch> batchesToUnstock, TxnEnums.BatchStatus batchStatus) {
        batchesToUnstock.forEach(warehouseBatch -> warehouseBatch.setBatchStatus(batchStatus));
    }

    public Optional<Warehouse> findWarehouseById(Long id) {
        return warehouseRepo.findById(id);
    }

    public Optional<List<Batch>> getAllBatchesInWarehouse(Long warehouseId) {
        return Optional.ofNullable(warehouseBatchRepo.findBatchesByWarehouseId(warehouseId));
    }
}





















