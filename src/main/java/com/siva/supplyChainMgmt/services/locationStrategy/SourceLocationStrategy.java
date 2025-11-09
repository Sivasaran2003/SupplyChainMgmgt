package com.siva.supplyChainMgmt.services.locationStrategy;

import com.siva.supplyChainMgmt.models.product.Batch;
import com.siva.supplyChainMgmt.models.transaction.Transaction;

import java.util.List;

public interface SourceLocationStrategy {
    void handleSource(Transaction transaction, List<Batch> batches);
}
