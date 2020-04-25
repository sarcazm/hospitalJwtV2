package poliklinika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poliklinika.model.Doctor;
import poliklinika.model.Record;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByLastName(String lastName);
    Doctor findByUsername(String username);

}
