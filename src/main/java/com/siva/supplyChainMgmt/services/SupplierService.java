package com.siva.supplyChainMgmt.services;

import com.siva.supplyChainMgmt.models.supplier.Supplier;
import org.springframework.stereotype.Service;
import com.siva.supplyChainMgmt.repositories.SupplierRepository;
import java.util.List;
import java.util.Optional;

@Service
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

    public Optional<Supplier> findSupplierByid(Long id) {
        return supplierRepo.findById(id);
    }
}
