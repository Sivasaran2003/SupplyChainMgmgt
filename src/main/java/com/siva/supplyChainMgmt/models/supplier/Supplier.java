package com.siva.supplyChainMgmt.models.supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import jakarta.persistence.Id;


import java.math.BigDecimal;

@Entity
@Data
public class Supplier {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    private String location;
    private BigDecimal rating;
}
