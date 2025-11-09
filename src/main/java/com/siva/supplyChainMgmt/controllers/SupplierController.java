package com.siva.supplyChainMgmt.controllers;

import com.siva.supplyChainMgmt.models.supplier.Supplier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.siva.supplyChainMgmt.services.SupplierService;

import java.util.List;

@RestController("SupplierApiController")
@RequestMapping("/api")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/supplier")
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        Supplier saved = supplierService.registerSupplier(supplier);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/supplier")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        if (suppliers.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(suppliers);
    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}