package com.siva.supplyChainMgmt.services.locationStrategy;

import com.siva.supplyChainMgmt.enums.TxnEnums;

public class LocationStrategyFactory {

    public final WarehouseSourceStrategy warehouseSourceStrategy;
    public final SupplierSourceStrategy supplierSourceStrategy;
    public final WarehouseDestinationStrategy warehouseDestinationStrategy;
    public final RetailerDestinationStrategy retailerDestinationStrategy;

    public LocationStrategyFactory(WarehouseSourceStrategy warehouseSourceStrategy,
                                   SupplierSourceStrategy supplierSourceStrategy,
                                   WarehouseDestinationStrategy warehouseDestinationStrategy,
                                   RetailerDestinationStrategy retailerDestinationStrategy) {
        this.retailerDestinationStrategy = retailerDestinationStrategy;
        this.supplierSourceStrategy = supplierSourceStrategy;
        this.warehouseDestinationStrategy = warehouseDestinationStrategy;
        this.warehouseSourceStrategy = warehouseSourceStrategy;
    }

    public SourceLocationStrategy getSourceLocationStrategyInstance(TxnEnums.stakeHolders stakeHolders) {
        if(stakeHolders.equals(TxnEnums.stakeHolders.WAREHOUSE)) {
            return warehouseSourceStrategy;
        } else if(stakeHolders.equals(TxnEnums.stakeHolders.SUPPLIER)) {
            return supplierSourceStrategy;
        }
        return null;
    }

    public DestinationLocationStrategy getDestinationLocationStrategyInstance(TxnEnums.stakeHolders stakeHolders) {
        if(stakeHolders.equals(TxnEnums.stakeHolders.WAREHOUSE)) {
            return warehouseDestinationStrategy;
        } else if(stakeHolders.equals(TxnEnums.stakeHolders.RETAILER)) {
            return retailerDestinationStrategy;
        }
        return null;
    }
}
