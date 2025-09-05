package services;

import repositories.RetailerRepository;

public class RetailerService {
    private final RetailerRepository retailerRepo;

    public RetailerService(RetailerRepository retailerRepo) {
        this.retailerRepo = retailerRepo;
    }
}
