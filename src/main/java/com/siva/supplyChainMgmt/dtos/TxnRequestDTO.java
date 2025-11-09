package com.siva.supplyChainMgmt.dtos;

import com.siva.supplyChainMgmt.enums.TxnEnums;
import lombok.Data;

import java.util.List;

@Data
public class TxnRequestDTO {
    private String fromLocation;
    private TxnEnums.stakeHolders fromLocationType;
    private String toLocation;
    private TxnEnums.stakeHolders toLocationType;
    private List<Long> batchIds;
}