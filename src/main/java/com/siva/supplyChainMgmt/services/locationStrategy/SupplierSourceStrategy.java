package com.siva.supplyChainMgmt.services.locationStrategy;

import com.siva.supplyChainMgmt.models.product.Batch;
import com.siva.supplyChainMgmt.models.transaction.Transaction;
import com.siva.supplyChainMgmt.services.SupplierService;

import java.util.List;
import java.util.NoSuchElementException;

public class SupplierSourceStrategy implements SourceLocationStrategy{
    SupplierService supplierService;

    public SupplierSourceStrategy(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void handleSource(Transaction transaction, List<Batch> batches) {
        supplierService.getSupplierById(transaction.getFromLocationId())
                .orElseThrow(() -> new NoSuchElementException("Source Supplier with ID " + transaction.getFromLocationId() + " not found."));
    }
}
