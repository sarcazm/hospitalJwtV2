package poliklinika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poliklinika.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    //можно бы было тут этого не прописывать?
    Patient findByLastName(String lastName);
    Patient findByUsername(String username);
    void deleteByUsername(String username);
}
