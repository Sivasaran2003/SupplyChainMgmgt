package com.siva.supplyChainMgmt.models.transaction;

import com.siva.supplyChainMgmt.enums.TxnEnums;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromLocationId;
    private Long toLocationId;
    private TxnEnums.stakeHolders fromLocationType;
    private TxnEnums.stakeHolders toLocationType;
    private TxnEnums.TxnStatus status;
    private LocalDateTime completedAt;
}