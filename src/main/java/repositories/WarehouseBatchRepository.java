package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseBatchRepository extends JpaRepository<WarehouseRepository, Long> {
}