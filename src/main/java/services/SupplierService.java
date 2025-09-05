package services;

import repositories.SupplierRepository;

public class SupplierService {
    private final SupplierRepository supplierRepo;

    public SupplierService(SupplierRepository supplierRepo) {
        this.supplierRepo = supplierRepo;
    }
}
