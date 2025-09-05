package com.siva.supplyChainMgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		models.retailer.Retailer.class,
		models.supplier.Supplier.class,
		models.product.Product.class,
		models.warehouse.Warehouse.class,
		models.product.Batch.class,
		models.transaction.Transaction.class,
		models.transaction.TransactionBatch.class,
		models.warehouse.WarehouseBatch.class
})

@EnableJpaRepositories(basePackageClasses = {
		models.retailer.Retailer.class,
		models.supplier.Supplier.class,
		models.product.Product.class,
		models.warehouse.Warehouse.class,
		models.product.Batch.class,
		models.transaction.Transaction.class,
		models.transaction.TransactionBatch.class,
		models.warehouse.WarehouseBatch.class
})
public class SupplyChainMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplyChainMgmtApplication.class, args);
	}

}
