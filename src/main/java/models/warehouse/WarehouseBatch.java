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
    Batch batch;

    public WarehouseBatch(Warehouse warehouse, Batch batch) {
        this.warehouse = warehouse;
        this.batch = batch;
        this.id = new WarehouseBatchId(batch.getId(), warehouse.getId());
    }
}

