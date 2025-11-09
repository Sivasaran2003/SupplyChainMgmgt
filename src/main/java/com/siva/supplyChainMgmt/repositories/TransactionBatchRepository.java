package com.siva.supplyChainMgmt.repositories;

import com.siva.supplyChainMgmt.models.transaction.TransactionBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionBatchRepository extends JpaRepository<TransactionBatch, Long> {
    @Query(value = "SELECT * FROM TransactionBatch WHERE transaction_id = :txnId", nativeQuery = true)
    List<TransactionBatch> findAllByTransactionId(@Param("txnId") Long transactionId);

    @Query(value = "SELECT * FROM TransactionBatch WHERE transaction_id = :txnId", nativeQuery = true)
    List<TransactionBatch> findAllByBatchId(@Param("batchId") Long batchId);
}
