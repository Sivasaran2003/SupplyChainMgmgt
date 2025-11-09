package com.siva.supplyChainMgmt.services.stateMachine;

import com.siva.supplyChainMgmt.enums.TxnEnums;
import com.siva.supplyChainMgmt.models.transaction.Transaction;
import com.siva.supplyChainMgmt.services.TransactionService;

public class InTransitTransaction implements TransactionState {
    @Override
    public Transaction executeTransaction(Transaction transaction, TransactionService txnService) {
        // Validations before state change
        txnService.processInventoryManagement(transaction, TxnEnums.TxnStatus.COMPLETED);
        transaction.setStatus(TxnEnums.TxnStatus.COMPLETED);
        return txnService.saveTransaction(transaction);
    }

    @Override
    public Transaction cancelTransaction(Transaction transaction, TransactionService txnService) {
        return null;
    }
}
