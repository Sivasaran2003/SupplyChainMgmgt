package services;

import models.product.Batch;
import models.transaction.Transaction;
import models.transaction.TransactionBatch;
import org.springframework.stereotype.Service;
import repositories.TransactionBatchRepository;
import repositories.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {
    private TransactionRepository txnRepo;
    private TransactionBatchRepository txnBatchRepo;

    public TransactionService(TransactionRepository txnRepo) {
        this.txnRepo = txnRepo;
    }

    public void saveTransaction(Transaction transaction) {
        txnRepo.save(transaction);
    }

    public void addBatchesToTransaction(List<Batch> batches, Transaction transaction) {
        for(Batch batch : batches) {
            TransactionBatch transactionBatch = new TransactionBatch(transaction, batch);
            txnBatchRepo.save(transactionBatch);
        }
    }
}
