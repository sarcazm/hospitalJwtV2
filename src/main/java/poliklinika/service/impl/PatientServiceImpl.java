package poliklinika.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import poliklinika.model.Patient;
import poliklinika.model.Record;
import poliklinika.model.Status;
import poliklinika.repository.PatientRepository;
import poliklinika.service.PatientService;

import java.util.List;

@Service
@Slf4j

public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, BCryptPasswordEncoder encoder) {
        this.patientRepository = patientRepository;

    }


    @Override
    public Patient add(Patient patient) {
        /*patient.setPassword(encoder.encode(patient.getPassword()));
        //на всякий случай
        patient.setRole("ROLE_USER");
        patient.setStatus(Status.ACTIVE);

        patientRepository.save(patient);*/

        log.info("IN add - patient: {} successfully added", patient);
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        log.info("IN getAll - patient: {} patients founded", patients.size());
        return patients;
    }

    @Override
    public Patient findByLastName(String lastName) {
        Patient patient = patientRepository.findByLastName(lastName);
        log.info("IN findByLastName - patient: {} found by patient lastname", patient, lastName);
        return patient;
    }

    @Override
    public Patient findById(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null){
            log.info("IN findById - no patient found by id: {}", id);
            return null;
        }
        log.info("IN findById - patient: {} found by id: {}", patient);
        return patient;
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
        log.info("IN delete - patient with id: {} successfully deleted");
    }

    @Override
    public Patient findByUsername(String username) {
        Patient result = patientRepository.findByUsername(username);
        log.info("IN findByUsername - patient: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public List<Record> getAllRecords(String username) {
        Patient patient = findByUsername(username);
        List<Record> records = patient.getRecords();
        log.info("IN getAllRecords - patient: {} found by records: {}", patient, records);
        return records;
    }
}
