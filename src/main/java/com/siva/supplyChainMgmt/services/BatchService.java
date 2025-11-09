package com.siva.supplyChainMgmt.services;

import com.siva.supplyChainMgmt.models.product.Batch;
import org.springframework.stereotype.Service;
import com.siva.supplyChainMgmt.repositories.BatchRepository;
import java.util.List;
import java.util.Optional;

@Service
public class BatchService {
    private final BatchRepository batchRepo;

    public BatchService(BatchRepository batchRepo) {
        this.batchRepo = batchRepo;
    }

    public Batch saveBatch(Batch batch) {
        return batchRepo.save(batch);
    }

    public Optional<Batch> getBatchById(Long id) {
        return batchRepo.findById(id);
    }

    public List<Batch> getBatchesByProductName(String productName) {
        return batchRepo.findBatchesByProductName(productName);
    }

    public Optional<Batch> updateBatch(Batch batch) {
        if(batch.getId() != null && batchRepo.existsById(batch.getId())) {
            return Optional.of(batchRepo.save(batch));
        }
        return Optional.empty();
    }

    public List<Batch> getAllBatches() {
        return batchRepo.findAll();
    }

    public boolean deleteBatch(Long id) {
        if(batchRepo.existsById(id)) {
            batchRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
