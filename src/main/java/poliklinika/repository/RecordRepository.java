package poliklinika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poliklinika.model.Record;


public interface RecordRepository extends JpaRepository<Record, Long> {
}
