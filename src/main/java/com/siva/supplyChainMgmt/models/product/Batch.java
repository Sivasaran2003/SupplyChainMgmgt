package com.siva.supplyChainMgmt.models.product;

import jakarta.persistence.*;
import lombok.*;
import com.siva.supplyChainMgmt.models.supplier.Supplier;

import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "batch", uniqueConstraints = {
        @UniqueConstraint(columnNames = "batchNo")
})

// TODO : add dto only with id while returning object of Batch
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
    private LocalDate expiryDate;
    @Column(nullable = false)
    private Integer qtyTotal;

    @PrePersist
    public void generateBatchNo() {
        if (this.batchNo == null) {
            this.batchNo = java.util.UUID.randomUUID().toString();
        }
    }
}