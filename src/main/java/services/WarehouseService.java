package services;

import repositories.WarehouseRepository;

public class WarehouseService {
    private final WarehouseRepository warehouseRepo;

    public WarehouseService(WarehouseRepository warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }
}
