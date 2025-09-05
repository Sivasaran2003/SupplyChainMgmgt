package models.warehouse;

import jakarta.persistence.*;
import models.product.Batch;

@Entity
public class WarehouseBatch {
    @EmbeddedId
    private WarehouseBatchId id = new WarehouseBatchId();

    @ManyToOne
    @MapsId("wareHouseId")
    Warehouse warehouse;

    @ManyToOne
    @MapsId("batchId")
    Batch batchId;
}

