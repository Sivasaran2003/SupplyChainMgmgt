package models.product;

import jakarta.persistence.*;
import lombok.*;
import models.supplier.Supplier;

import java.time.LocalDate;

@Entity
@Data
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

    public Batch(Supplier supplier) {
        this.supplier = supplier;
    }
}