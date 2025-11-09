package com.siva.supplyChainMgmt.repositories;

import com.siva.supplyChainMgmt.models.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
