package com.siva.supplyChainMgmt.services;

import com.siva.supplyChainMgmt.models.retailer.Retailer;
import org.springframework.stereotype.Service;
import com.siva.supplyChainMgmt.repositories.RetailerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RetailerService {
    private final RetailerRepository retailerRepo;

    public RetailerService(RetailerRepository retailerRepo) {
        this.retailerRepo = retailerRepo;
    }

    public Retailer registerRetailer(Retailer retailer) {
        return retailerRepo.save(retailer);
    }
    
    public Optional<Retailer> getRetailerById(Long id) {
        return retailerRepo.findById(id);
    }

    public List<Retailer> getAllRetailers() {
        return retailerRepo.findAll();
    }

    public void deleteRetailer(Long id) {
        retailerRepo.deleteById(id);
    }

    public Optional<Retailer> findRetailerById(Long id) {
        return retailerRepo.findById(id);
    }
}
