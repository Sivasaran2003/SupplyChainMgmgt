package com.siva.supplyChainMgmt.models.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Data
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable=false) private String name;
    private String category;
    @NotNull
    private Long price;
}

