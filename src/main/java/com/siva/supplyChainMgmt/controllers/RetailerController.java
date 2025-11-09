package com.siva.supplyChainMgmt.controllers;

import com.siva.supplyChainMgmt.models.retailer.Retailer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.siva.supplyChainMgmt.services.RetailerService;

import java.util.List;

@RestController("RetailerApiController")
@RequestMapping("/api")
public class RetailerController {
    private final RetailerService retailerService;

    public RetailerController(RetailerService retailerService) {
        this.retailerService = retailerService;
    }

    @PostMapping("/retailer")
    public ResponseEntity<Retailer> createRetailer(@RequestBody Retailer retailer) {
        Retailer saved = retailerService.registerRetailer(retailer);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/retailer/{id}")
    public ResponseEntity<Retailer> getRetailerById(@PathVariable Long id) {
        return retailerService.getRetailerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/retailer")
    public ResponseEntity<List<Retailer>> getAllRetailers() {
        List<Retailer> retailers = retailerService.getAllRetailers();
        if (retailers.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(retailers);
    }

    @DeleteMapping("/retailer/{id}")
    public ResponseEntity<Void> deleteRetailer(@PathVariable Long id) {
        retailerService.deleteRetailer(id);
        return ResponseEntity.noContent().build();
    }
}