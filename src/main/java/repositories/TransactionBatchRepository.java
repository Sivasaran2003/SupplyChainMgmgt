package repositories;

import models.transaction.TransactionBatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionBatchRepository extends JpaRepository<TransactionBatch, Long> {
}
