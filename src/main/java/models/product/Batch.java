package models.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import models.supplier.Supplier;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
@Data
public class Batch {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false) // foreign key to product
    private Product product;
    @ManyToOne(optional = false) // foreign key to supplier
    private Supplier supplier;
    @Column(nullable = false)
    private String batchNo;
    @Column(nullable = false)
    private LocalDate manufactureDate;
    @Column(nullable = false)
    private LocalDate expiryDate;
    @Column(nullable = false)
    private Integer qtyTotal;
}
