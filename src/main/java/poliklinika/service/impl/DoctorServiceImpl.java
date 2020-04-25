package poliklinika.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poliklinika.dto.DoRecordDto;
import poliklinika.model.Doctor;
import poliklinika.model.Patient;
import poliklinika.model.Record;
import poliklinika.model.Status;
import poliklinika.repository.DoctorRepository;
import poliklinika.repository.PatientRepository;
import poliklinika.repository.RecordRepository;
import poliklinika.repository.ServiceRepository;
import poliklinika.service.DoctorService;
import poliklinika.service.PatientService;

import java.util.List;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final RecordRepository recordRepository;
    private final PatientRepository patientRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, RecordRepository recordRepository, PatientRepository patientRepository, ServiceRepository serviceRepository) {
        this.doctorRepository = doctorRepository;
        this.recordRepository = recordRepository;
        this.patientRepository = patientRepository;
        this.serviceRepository = serviceRepository;

    }

    @Override
    public Doctor add(Doctor doctor) {
        /*doctor.setPassword(encoder.encode(doctor.getPassword()));
        //на всякий случай
        doctor.setRole("ROLE_ADMIN");
        doctor.setStatus(Status.ACTIVE);

        doctorRepository.save(doctor);*/

        log.info("IN add - doctor: {} successfully added", doctor);
        return doctor;
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        log.info("IN getAll - doctor: {} doctors founded", doctors.size());
        return doctors;
    }

    @Override
    public Doctor findByLastName(String lastName) {
        Doctor doctor = doctorRepository.findByLastName(lastName);
        log.info("IN findByLastName - doctor: {} found by doctor lastname", doctor, lastName);
        return doctor;
    }

    @Override
    public Doctor findById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor == null){
            log.info("IN findById - no doctor found by id: {}", id);
            return null;
        }
        log.info("IN findById - doctor: {} found by id: {}", doctor);
        return doctor;
    }

    @Override
    public void delete(Long id) {
        doctorRepository.deleteById(id);
        log.info("IN delete - doctor with id: {} successfully deleted");
    }

    @Override
    public void deletePatient(String username) {
        patientRepository.deleteByUsername(username);
        log.info("IN deletePatient - patient with username: {} successfully deleted", username);

    }

    @Override
    public Doctor findByUsername(String username) {
        Doctor result = doctorRepository.findByUsername(username);
        log.info("IN findByUsername - doctor: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public List<Record> getAllRecords(String username) {
        Doctor doctor = findByUsername(username);
        List<Record> records = doctor.getRecords();
        log.info("IN getAllRecords - doctor: {} found by records: {}", doctor, records);
        return records;
    }

    @Override
    public Record addRecord(DoRecordDto doRecordDto, String usernameDoctor) {
        Record record = new Record();
        record.setDate(doRecordDto.getDate());
        record.setPatinetId(patientRepository.findByUsername(doRecordDto.getPatientUsername()));
        record.setDoctorId(doctorRepository.findByUsername(usernameDoctor));
        record.setServiceId(serviceRepository.findByName(doRecordDto.getServiceName()));
        record.setStatus(Status.ACTIVE);
        recordRepository.save(record);
        log.info("IN addRecord - doctor: added record: {}", record);
        return record;
    }
}
