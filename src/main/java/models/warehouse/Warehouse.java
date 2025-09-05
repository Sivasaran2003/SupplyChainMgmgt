package models.warehouse;

import jakarta.persistence.*;
import jakarta.persistence.Id;


@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
}