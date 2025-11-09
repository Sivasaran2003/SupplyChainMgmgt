package com.siva.supplyChainMgmt.services.locationStrategy;

import com.siva.supplyChainMgmt.models.product.Batch;
import com.siva.supplyChainMgmt.models.transaction.Transaction;
import com.siva.supplyChainMgmt.models.warehouse.Warehouse;
import com.siva.supplyChainMgmt.models.warehouse.WarehouseBatch;
import com.siva.supplyChainMgmt.services.WarehouseService;

import java.util.List;
import java.util.NoSuchElementException;

public class WarehouseDestinationStrategy implements DestinationLocationStrategy{

    WarehouseService warehouseService;

    public WarehouseDestinationStrategy(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Override
    public void handleDestination(Transaction transaction, List<Batch> batches) {
        Warehouse destinationWarehouse = warehouseService.getWarehouseById(transaction.getToLocationId())
                .orElseThrow(() -> new NoSuchElementException("Destination Warehouse with ID " + transaction.getToLocationId() + " not found."));

        // Perform Stocking
        warehouseService.stockBatches(destinationWarehouse, batches);
    }
}
