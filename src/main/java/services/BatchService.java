package services;

import models.product.Batch;
import repositories.BatchRepository;

public class BatchService {
    private final BatchRepository batchRepo;

    public BatchService(BatchRepository batchRepo) {
        this.batchRepo = batchRepo;
    }

    public void saveBatch(Batch batch) {
        batchRepo.save(batch);
    }
}
