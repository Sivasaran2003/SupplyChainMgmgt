package com.siva.supplyChainMgmt.models.retailer;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Retailer {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    private String location;
}
