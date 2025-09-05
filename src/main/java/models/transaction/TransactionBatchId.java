package models.transaction;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class TransactionBatchId implements Serializable {
    private Long transactionId;
    private Long batchId;
}
