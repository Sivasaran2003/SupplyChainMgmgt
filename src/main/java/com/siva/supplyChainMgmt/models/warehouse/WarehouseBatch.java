package com.siva.supplyChainMgmt.models.warehouse;

import com.siva.supplyChainMgmt.enums.TxnEnums;
import jakarta.persistence.*;
import com.siva.supplyChainMgmt.models.product.Batch;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class WarehouseBatch {
    @EmbeddedId
    private WarehouseBatchId id = new WarehouseBatchId();

    @ManyToOne
    @MapsId("wareHouseId")
    Warehouse warehouse;

    @ManyToOne
    @MapsId("batchId")
    Batch batch;

    TxnEnums.BatchStatus batchStatus;

    public WarehouseBatch(Warehouse warehouse, Batch batch) {
        this.warehouse = warehouse;
        this.batch = batch;
        this.id = new WarehouseBatchId(batch.getId(), warehouse.getId());
    }
}

