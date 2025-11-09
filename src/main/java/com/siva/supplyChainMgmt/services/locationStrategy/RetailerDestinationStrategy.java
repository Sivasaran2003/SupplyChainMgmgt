package com.siva.supplyChainMgmt.services.locationStrategy;

import com.siva.supplyChainMgmt.models.product.Batch;
import com.siva.supplyChainMgmt.models.transaction.Transaction;
import com.siva.supplyChainMgmt.services.RetailerService;

import java.util.List;
import java.util.NoSuchElementException;

public class RetailerDestinationStrategy implements DestinationLocationStrategy{
    RetailerService retailerService;

    public RetailerDestinationStrategy(RetailerService retailerService) {
        this.retailerService = retailerService;
    }

    @Override
    public void handleDestination(Transaction transaction, List<Batch> batches) {
        retailerService.getRetailerById(transaction.getToLocationId())
                .orElseThrow(() -> new NoSuchElementException("Destination Retailer with ID " + transaction.getToLocationId() + " not found."));
    }
}
