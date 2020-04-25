package poliklinika.service;

import poliklinika.model.Patient;
import poliklinika.model.Record;

import java.util.List;

public interface PatientService {

    Patient add(Patient patient);

    List<Patient> getAllPatients();

    Patient findByLastName(String lastName);

    Patient findById(Long id);

    void delete(Long id);

    Patient findByUsername(String username);

    List<Record> getAllRecords(String username);
}
