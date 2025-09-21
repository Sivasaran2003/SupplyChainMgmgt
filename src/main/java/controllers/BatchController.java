package controllers;

import models.product.Batch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.BatchService;

import java.util.List;

@RestController("/api")
public class BatchController {
    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping("/batch")
    public ResponseEntity<Batch> createBatch(@RequestBody Batch batch) {
        Batch saved = batchService.saveBatch(batch);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/batch")
    public ResponseEntity<List<Batch>> getAllBatches() {
        List<Batch> batches = batchService.getAllBatches();
        if(batches.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(batches);
    }

    @GetMapping("/batch/{id}")
    public ResponseEntity<Batch> getBatchById(@PathVariable Long id) {
        return batchService.getBatchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/batch")
    public ResponseEntity<Batch> updateBatch(@RequestBody Batch batch) {
        return batchService.updateBatch(batch)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/batch/{id}")
    public ResponseEntity<String> deleteBatch(@PathVariable Long id) {
        boolean deleted = batchService.deleteBatch(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
