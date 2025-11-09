package com.siva.supplyChainMgmt.services.stateMachine;

import com.siva.supplyChainMgmt.models.transaction.Transaction;
import com.siva.supplyChainMgmt.services.TransactionService;

public class CompletedTransaction implements TransactionState {
    @Override
    public Transaction executeTransaction(Transaction transaction, TransactionService service) {
        throw new IllegalArgumentException("Cannot execute an already COMPLETED transaction.");
    }

    @Override
    public Transaction cancelTransaction(Transaction transaction, TransactionService service) {
        throw new IllegalArgumentException("Cannot cancel a COMPLETED transaction.");
    }
}
