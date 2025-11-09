package com.siva.supplyChainMgmt.services.locationStrategy;


import com.siva.supplyChainMgmt.enums.TxnEnums;
import com.siva.supplyChainMgmt.models.product.Batch;
import com.siva.supplyChainMgmt.models.transaction.Transaction;
import com.siva.supplyChainMgmt.models.warehouse.Warehouse;
import com.siva.supplyChainMgmt.models.warehouse.WarehouseBatch;
import com.siva.supplyChainMgmt.services.WarehouseService;

import java.util.List;
import java.util.NoSuchElementException;

public class WarehouseSourceStrategy implements SourceLocationStrategy {
    WarehouseService warehouseService;

    public WarehouseSourceStrategy(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Override
    public void handleSource(Transaction transaction, List<Batch> batches) {
        Warehouse sourceWarehouse = warehouseService.getWarehouseById(transaction.getFromLocationId())
                .orElseThrow(() -> new NoSuchElementException("Source Warehouse with ID " + transaction.getFromLocationId() + " not found."));

        List<WarehouseBatch> stockToUnstock = warehouseService.findByWarehouseIdAndBatches(sourceWarehouse.getId(), batches);

        if (stockToUnstock.size() != batches.size()) {
            throw new IllegalArgumentException("Not all batches are currently stocked in the source warehouse.");
        }

        // Perform the Soft Delete (Status Update)
        warehouseService.unstockBatches(stockToUnstock, TxnEnums.BatchStatus.SHIPPED);
    }
}
