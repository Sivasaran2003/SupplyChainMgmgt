package com.siva.supplyChainMgmt;

import com.siva.supplyChainMgmt.models.product.Batch;
import com.siva.supplyChainMgmt.models.product.Product;
import com.siva.supplyChainMgmt.models.retailer.Retailer;
import com.siva.supplyChainMgmt.models.supplier.Supplier;
import com.siva.supplyChainMgmt.models.transaction.Transaction;
import com.siva.supplyChainMgmt.models.transaction.TransactionBatch;
import com.siva.supplyChainMgmt.models.warehouse.Warehouse;
import com.siva.supplyChainMgmt.models.warehouse.WarehouseBatch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		Retailer.class,
		Supplier.class,
		Product.class,
		Warehouse.class,
		Batch.class,
		Transaction.class,
		TransactionBatch.class,
		WarehouseBatch.class
})

@EnableJpaRepositories(basePackages = "com.siva.supplyChainMgmt.repositories")
public class SupplyChainMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplyChainMgmtApplication.class, args);
	}

}
