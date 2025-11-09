package com.siva.supplyChainMgmt.services;

import com.siva.supplyChainMgmt.dtos.BatchHistoryDTO;
import com.siva.supplyChainMgmt.dtos.TxnRequestDTO;
import com.siva.supplyChainMgmt.enums.TxnEnums;
import com.siva.supplyChainMgmt.mappers.TransactionMapper;
import com.siva.supplyChainMgmt.models.product.Batch;
import com.siva.supplyChainMgmt.models.transaction.Transaction;
import com.siva.supplyChainMgmt.models.transaction.TransactionBatch;
import com.siva.supplyChainMgmt.models.warehouse.Warehouse;
import com.siva.supplyChainMgmt.models.warehouse.WarehouseBatch;
import com.siva.supplyChainMgmt.repositories.WarehouseBatchRepository;
import com.siva.supplyChainMgmt.services.locationStrategy.DestinationLocationStrategy;
import com.siva.supplyChainMgmt.services.locationStrategy.LocationStrategyFactory;
import com.siva.supplyChainMgmt.services.locationStrategy.SourceLocationStrategy;
import com.siva.supplyChainMgmt.services.stateMachine.CompletedTransaction;
import com.siva.supplyChainMgmt.services.stateMachine.InTransitTransaction;
import com.siva.supplyChainMgmt.services.stateMachine.PendingTransaction;
import com.siva.supplyChainMgmt.services.stateMachine.TransactionState;
import org.springframework.stereotype.Service;
import com.siva.supplyChainMgmt.repositories.BatchRepository;
import com.siva.supplyChainMgmt.repositories.TransactionBatchRepository;
import com.siva.supplyChainMgmt.repositories.TransactionRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository txnRepo;
    private final TransactionBatchRepository txnBatchRepo;
    private final BatchRepository batchRepo;
    private final TransactionMapper txnMapper;
    private final SupplierService supplierService;
    private final WarehouseService warehouseService;
    private final RetailerService retailerService;
    private final WarehouseBatchRepository warehouseBatchRepo;
    private final Map<TxnEnums.TxnStatus, TransactionState> txnStateMap;
    private final LocationStrategyFactory locationStrategyFactory;

    public TransactionService(TransactionRepository txnRepo, TransactionBatchRepository txnBatchRepo,
                              BatchRepository batchRepo, TransactionMapper txnMapper, SupplierService supplierService,
                              WarehouseService warehouseService, RetailerService retailerService, WarehouseBatchRepository warehouseBatchRepo,
                              LocationStrategyFactory locationStrategyFactory) {
        this.txnRepo = txnRepo;
        this.txnBatchRepo = txnBatchRepo;
        this.batchRepo = batchRepo;
        this.txnMapper = txnMapper;
        this.supplierService = supplierService;
        this.warehouseService = warehouseService;
        this.retailerService = retailerService;
        this.warehouseBatchRepo = warehouseBatchRepo;
        this.txnStateMap = new HashMap<>();
        txnStateMap.put(TxnEnums.TxnStatus.PENDING, new PendingTransaction());
        txnStateMap.put(TxnEnums.TxnStatus.IN_TRANSIT, new InTransitTransaction());
        txnStateMap.put(TxnEnums.TxnStatus.COMPLETED, new CompletedTransaction());
        this.locationStrategyFactory = locationStrategyFactory;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return txnRepo.save(transaction);
    }

    public void processInventoryManagement(Transaction transaction, TxnEnums.TxnStatus nextState) {

        // 1. Prevent re-execution
        if (transaction.getStatus().equals(nextState)) {
            throw new IllegalArgumentException("Transaction is already " + nextState.name());
        }

        // 2. Get all batches for a transaction
        List<Batch> batches = getBatchesByTransactionId(transaction.getId());
        if (batches.isEmpty()) {
            throw new NoSuchElementException("No batches linked to transaction ID " + transaction.getId());
        }

        // 3. Source Location updates
        SourceLocationStrategy sourceLocationStrategy = locationStrategyFactory.getSourceLocationStrategyInstance(transaction.getFromLocationType());
        sourceLocationStrategy.handleSource(transaction, batches);

        // 4. Destination location updates
        DestinationLocationStrategy destinationLocationStrategy = locationStrategyFactory.getDestinationLocationStrategyInstance(transaction.getToLocationType());
        destinationLocationStrategy.handleDestination(transaction, batches);

        // 5. Update Status to nextStatus and save
        transaction.setStatus(nextState);
        txnRepo.save(transaction);
    }

    // create a new transaction with batches to be transferred
    public Transaction createTransactionWithBatches(TxnRequestDTO transactionRequest) {

        // 1. Save the transaction details
        Transaction transaction = txnRepo.save(txnMapper.toEntity(transactionRequest));

        // 2. Get all batches using the list of batch IDs provided
        List<Batch> batches = batchRepo.findAllById(transactionRequest.getBatchIds());
        if(batches.isEmpty()) throw new IllegalArgumentException("no batches choosen for transaction");

        // 3. Map transaction and batches and save
        for(Batch batch : batches) {
            TransactionBatch txnBatch = new TransactionBatch(transaction, batch);
            txnBatchRepo.save(txnBatch);
        }

        return transaction;
    }

    // get all batches present for a transaction
    List<Batch> getBatchesByTransactionId(Long transactionId) {
        // 1. Fetch all batches for a transaction
        List<TransactionBatch> txnBatches = txnBatchRepo.findAllByTransactionId(transactionId);

        // 2. Filter only batches out of transactionBatches
        return txnBatches.stream()
                .map(TransactionBatch::getBatch)
                .toList();
    }

    // Update Transaction Status to "COMPLETED"
    public Transaction executeTransaction(Long transactionId) throws NoSuchElementException, IllegalArgumentException {
        // 1. Fetch Transaction and Validate Existence
        Transaction transaction = txnRepo.findById(transactionId)
                .orElseThrow(() -> new NoSuchElementException("Transaction with ID " + transactionId + " not found."));
        TransactionState transactionState = txnStateMap.get(transaction.getStatus());

        transactionState.executeTransaction(transaction, this);

        return transaction;
    }

    public void cancelTransaction(Long transactionId) {
        // 1. Fetch Transaction and Validate Existence
        Transaction transaction = txnRepo.findById(transactionId)
                .orElseThrow(() -> new NoSuchElementException("Transaction with ID " + transactionId + " not found."));

        // 2. Prevent Cancellation if already completed
        if (transaction.getStatus().equals(TxnEnums.TxnStatus.COMPLETED)) {
            throw new IllegalArgumentException("Cannot cancel a completed transaction");
        }

        // 3. Prevent re-cancellation
        if (transaction.getStatus().equals(TxnEnums.TxnStatus.CANCELLED)) {
            throw new IllegalArgumentException("Transaction is already cancelled.");
        }

        // 3. get all the batches involved in Transaction
        List<Batch> batches = getBatchesByTransactionId(transactionId);

        // 4. Roll Back Inventory Changes (if the transaction involved an unstocking attempt)
        if (transaction.getFromLocationType() == TxnEnums.stakeHolders.WAREHOUSE) {
            Warehouse sourceWarehouse = warehouseService.getWarehouseById(transaction.getFromLocationId())
                    .orElseThrow(() -> new NoSuchElementException("Source Warehouse with ID " + transaction.getFromLocationId() + " not found."));

            // CRITICAL: Get the specific WarehouseBatch records that are SHIPPED
            List<WarehouseBatch> updatedStock = warehouseBatchRepo.findShippedBatches(sourceWarehouse.getId(), batches);

            if (updatedStock.size() != batches.size()) {
                throw new IllegalArgumentException("Not all batches are currently stocked in the source warehouse.");
            }

            // Update the status of affected stock to STOCKED
            updatedStock.forEach(batch -> {
                // Only roll back if the status suggests it was partially processed
                if (batch.getBatchStatus().equals(TxnEnums.BatchStatus.SHIPPED)) {
                    batch.setBatchStatus(TxnEnums.BatchStatus.STOCKED);
                }
            });

            warehouseBatchRepo.saveAll(updatedStock);
        }

        // 5. Update Transaction Status
        transaction.setStatus(TxnEnums.TxnStatus.CANCELLED);
        txnRepo.save(transaction);
    }

    public List<BatchHistoryDTO> getBatchTravelHistory(Long batchId) {

        // 1. Validate that the Batch exists
        batchRepo.findById(batchId)
                .orElseThrow(() -> new NoSuchElementException("Batch with ID " + batchId + " not found."));

        // 2. Find all TransactionBatch join entities for this batch
        List<TransactionBatch> txnBatches = txnBatchRepo.findAllByBatchId(batchId);

        if (txnBatches.isEmpty()) {
            return List.of(); // Return an empty list if no history is found
        }

        return txnBatches.stream()
                .map(TransactionBatch::getTransaction)
                .sorted(Comparator.comparing(Transaction::getCompletedAt))
                .map(txn -> {
                    // Map the Transaction entity to the DTO
                    BatchHistoryDTO dto = new BatchHistoryDTO();
                    dto.setTransactionId(txn.getId());
                    dto.setTransactionStatus(txn.getStatus());
                    dto.setFromLocationId(txn.getFromLocationId());
                    dto.setFromLocationType(txn.getFromLocationType());
                    dto.setToLocationId(txn.getToLocationId());
                    dto.setToLocationType(txn.getToLocationType());
                    return dto;
                })
                .collect(Collectors.toList());

    }
}
