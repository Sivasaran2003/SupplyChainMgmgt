package models.warehouse;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class WarehouseBatchId implements Serializable {
    private Long wareHouseId;
    private Long batchId;
}
