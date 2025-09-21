package services;

import models.product.Batch;
import models.transaction.Transaction;
import models.transaction.TransactionBatch;
import org.springframework.stereotype.Service;
import repositories.BatchRepository;
import repositories.TransactionBatchRepository;
import repositories.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository txnRepo;
    private final TransactionBatchRepository txnBatchRepo;
    private final BatchRepository batchRepo;

    public TransactionService(TransactionRepository txnRepo, TransactionBatchRepository txnBatchRepo,
                              BatchRepository batchRepo) {
        this.txnRepo = txnRepo;
        this.txnBatchRepo = txnBatchRepo;
        this.batchRepo = batchRepo;
    }

    public void saveTransaction(Transaction transaction) {
        txnRepo.save(transaction);
    }

    public void addBatchesToTransaction(List<Long> batchIds, Transaction transaction) {
        List<Batch> batches = batchRepo.findAllById(batchIds);
        List<TransactionBatch> txnBatches = new ArrayList<>();

        for(Batch batch : batches) {
            TransactionBatch txnBatch = new TransactionBatch(transaction, batch);
            txnBatches.add(txnBatch);
        }

        txnBatchRepo.saveAll(txnBatches);
    }
}
