package com.siva.supplyChainMgmt.repositories;

import com.siva.supplyChainMgmt.models.product.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

    @Query("SELECT b.* where batch b INNER JOIN product p ON batch.product_id = p.id" +
            "WHERE p.name ILIKE CONCAT('%', :productName, '%')")
    public List<Batch> findBatchesByProductName(@Param("productName") String productName);
}