package models.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import jakarta.persistence.Id;


@Entity
@Data
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable=false) private String name;
    private String category;
}

