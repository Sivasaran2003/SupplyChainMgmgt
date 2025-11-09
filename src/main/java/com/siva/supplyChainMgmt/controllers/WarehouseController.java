package com.siva.supplyChainMgmt.controllers;

import com.siva.supplyChainMgmt.models.product.Batch;
import com.siva.supplyChainMgmt.models.warehouse.Warehouse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.siva.supplyChainMgmt.services.WarehouseService;

import java.util.List;
import java.util.Optional;

@RestController("WarehouseApiController")
@RequestMapping("/api")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping("/warehouse")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse saved = warehouseService.registerWarehouse(warehouse);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/warehouse/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/warehouse")
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        if (warehouses.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(warehouses);
    }

    @DeleteMapping("/warehouse/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{warehouseId}/batches")
    public ResponseEntity<Optional<List<Batch>>> getBatchesInWarehouse(@PathVariable Long warehouseId) {
        return ResponseEntity.ok(warehouseService.getAllBatchesInWarehouse(warehouseId));
    }
}