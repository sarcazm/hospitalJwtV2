package poliklinika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poliklinika.model.Service;


public interface ServiceRepository extends JpaRepository<Service, Long> {
    Service findByName(String name);
}
