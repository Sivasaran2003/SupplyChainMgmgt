package com.siva.supplyChainMgmt.services.stateMachine;

import com.siva.supplyChainMgmt.models.transaction.Transaction;
import com.siva.supplyChainMgmt.services.TransactionService;

public interface TransactionState {
    Transaction executeTransaction(Transaction transaction, TransactionService txnService);
    Transaction cancelTransaction(Transaction transaction, TransactionService txnService);
}
