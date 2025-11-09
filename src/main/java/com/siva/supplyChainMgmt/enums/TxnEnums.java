package com.siva.supplyChainMgmt.enums;

public class TxnEnums {
    public enum stakeHolders {
        SUPPLIER,
        RETAILER,
        WAREHOUSE
    }

    public enum TxnStatus {
        DRAFT, // transaction initiated but not yet approved or started
        PENDING, // awaiting approval or resources
        IN_TRANSIT, // goods left 'from' location
        COMPLETED, // goods arrived at 'to' location and inventory is updated
        CANCELLED, // txn was aborted before movement
        FAILED, // attempted to execute, but failed
        HOLD // temporarily paused to an external issue
    }

    public enum BatchStatus {
        SHIPPED, // shipped out of warehouse
        STOCKED // stocked into warehouse
    }
}
