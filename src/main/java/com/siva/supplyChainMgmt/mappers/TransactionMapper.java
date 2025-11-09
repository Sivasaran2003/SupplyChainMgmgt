package com.siva.supplyChainMgmt.mappers;

import com.siva.supplyChainMgmt.dtos.TxnRequestDTO;
import com.siva.supplyChainMgmt.models.transaction.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", expression = "java(com.siva.supplyChainMgmt.enums.TxnEnums.TxnStatus.PENDING)")
    Transaction toEntity(TxnRequestDTO transaction);
}
