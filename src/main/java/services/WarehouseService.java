package services;

import models.warehouse.Warehouse;
import repositories.WarehouseRepository;

import java.util.List;
import java.util.Optional;

public class WarehouseService {
    private final WarehouseRepository warehouseRepo;

    public WarehouseService(WarehouseRepository warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }

    public Warehouse registerWarehouse(Warehouse warehouse) {
        return warehouseRepo.save(warehouse);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepo.findAll();
    }

    public void deleteWarehouse(Long id) {
        warehouseRepo.deleteById(id);
    }

    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepo.findById(id);
    }
}
