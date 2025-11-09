package com.siva.supplyChainMgmt.dtos;

import com.siva.supplyChainMgmt.enums.TxnEnums;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BatchHistoryDTO {
    // Basic Transaction info
    private Long transactionId;
    private TxnEnums.TxnStatus transactionStatus;

    private Long fromLocationId;
    private TxnEnums.stakeHolders fromLocationType;

    private Long toLocationId;
    private TxnEnums.stakeHolders toLocationType;

     private LocalDateTime completedAt;
}