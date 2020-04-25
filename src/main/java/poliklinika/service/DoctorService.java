package poliklinika.service;

import poliklinika.dto.DoRecordDto;
import poliklinika.model.Doctor;
import poliklinika.model.Patient;
import poliklinika.model.Record;

import java.util.List;

public interface DoctorService {
    Doctor add(Doctor doctor);

    List<Doctor> getAll();

    Doctor findByLastName(String lastName);

    Doctor findById(Long id);

    void delete(Long id);
    void deletePatient(String username);

    Doctor findByUsername(String username);

    List<Record> getAllRecords(String username);

    Record addRecord(DoRecordDto doRecordDto, String usernameDoctor);
}
