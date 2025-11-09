package com.siva.supplyChainMgmt.models.transaction;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.siva.supplyChainMgmt.models.product.Batch;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionBatch {

    @EmbeddedId
    private TransactionBatchId transactionBatchId;

    @ManyToOne
    @MapsId("transactionId")
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @ManyToOne
    @MapsId("batchId")
    @JoinColumn(name = "batch_id")
    private Batch batch;

    public TransactionBatch(Transaction transaction, Batch batch) {
        this.transactionBatchId = new TransactionBatchId(transaction.getId(), batch.getId());
        this.transaction = transaction;
        this.batch = batch;
    }
}
