package com.siva.supplyChainMgmt.controllers;

import com.siva.supplyChainMgmt.dtos.BatchHistoryDTO;
import com.siva.supplyChainMgmt.dtos.TxnRequestDTO;
import com.siva.supplyChainMgmt.models.transaction.Transaction;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.siva.supplyChainMgmt.services.TransactionService;

import java.util.List;

@RestController("TransactionApiController")
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService txnService;

    public TransactionController(TransactionService txnService) {
        this.txnService = txnService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> addTransaction(TxnRequestDTO transaction) {
        return ResponseEntity.ok(txnService.createTransactionWithBatches(transaction));
    }

    @PutMapping("/transaction/{transactionId}/execute")
    public ResponseEntity<Transaction> executeTransaction(@PathVariable Long transactionId) {
        return ResponseEntity.ok(txnService.executeTransaction(transactionId));
    }

    @PutMapping("/transaction/{transactionId}/cancel")
    public ResponseEntity<String> cancelTransaction(@PathVariable Long transactionId) {
        txnService.cancelTransaction(transactionId);
        return ResponseEntity.ok("Cancelled Transaction");
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<BatchHistoryDTO>> getBatchHistory(@PathVariable Long id) {
        List<BatchHistoryDTO> history = txnService.getBatchTravelHistory(id);

        if (history.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(history);
    }
}
