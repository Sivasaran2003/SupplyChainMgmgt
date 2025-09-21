package services;

import models.retailer.Retailer;
import repositories.RetailerRepository;

import java.util.List;
import java.util.Optional;

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
}
