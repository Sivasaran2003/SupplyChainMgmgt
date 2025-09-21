package services;

import models.supplier.Supplier;
import repositories.SupplierRepository;

import java.util.List;
import java.util.Optional;

public class SupplierService {
    private final SupplierRepository supplierRepo;

    public SupplierService(SupplierRepository supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    public Supplier registerSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepo.findById(id);
    }


    public List<Supplier> getAllSuppliers() {
        return supplierRepo.findAll();
    }

    public void deleteSupplier(Long supplierId) {
        supplierRepo.deleteById(supplierId);
    }
}
