package poliklinika.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import poliklinika.dto.DoRecordDto;
import poliklinika.dto.PatientDeleteDto;
import poliklinika.dto.PatientDto;
import poliklinika.dto.RecordDto;
import poliklinika.model.Patient;
import poliklinika.model.Record;
import poliklinika.model.Status;
import poliklinika.repository.PatientRepository;
import poliklinika.repository.UserRepository;
import poliklinika.security.jwt.JwtTokenProvider;
import poliklinika.service.DoctorService;
import poliklinika.service.PatientService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/doctor")
public class DoctorRestController {

    private final JwtTokenProvider jwtTokenProvider;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    @Autowired
    public DoctorRestController(JwtTokenProvider jwtTokenProvider, DoctorService doctorService, PatientService patientService,
                                PatientRepository patientRepository, UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.patientRepository= patientRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/all-records")
    public ResponseEntity<List<RecordDto>> getAllRecords(HttpServletRequest req){
        List<Record> records = doctorService.getAllRecords(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        List<RecordDto> recordDtoList = records
                .stream()
                .map(record -> RecordDto.fromRecord(record))
                .collect(Collectors.toList());

        if(records == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(recordDtoList, HttpStatus.OK);
    }
    @PostMapping("/do-record")
    public ResponseEntity<RecordDto> doRecord(@RequestBody DoRecordDto doRecordDto, HttpServletRequest req){
        Record record = doctorService.addRecord(doRecordDto, jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        return new ResponseEntity<RecordDto>(RecordDto.fromRecord(record), HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("delete/patient/username/{username}")
    public ResponseEntity deletePatient(@PathVariable("username") String username){
        //почему то не удаляет из БД
        //Как лучше удалять? прям здесь? Или в DoctorsServiceImpl?
        /*boolean isDataBase = false;
        boolean deleteInDataBase = false;
        String yesOrNo = "no";
        Patient patient = patientService.findByUsername(username);
        //patient.setStatus(Status.DELETED);
        if (patient != null){
            isDataBase = true;
            patientService.delete(patient.getId());
            patient = patientService.findByUsername(username);
            if (patient == null)deleteInDataBase = true;
        }
        System.out.println("Нашли? " + isDataBase);
        System.out.println("Удалили? " + deleteInDataBase);*/

        //этот метод не работает для удаления
        //doctorService.deletePatient(username);
       // patientRepository.deleteByUsername(username);

        //System.out.println(userRepository.findByUsername("pat5"));
        patientRepository.delete(patientRepository.findByUsername("pat5"));
        //userRepository.delete(userRepository.findByUsername("pat5"));

        Map<Object, Object> res = new HashMap<>();
        res.put("patient", username);
        //if (isDataBase && deleteInDataBase)yesOrNo = "yes";
        //res.put("delete", yesOrNo);
        return ResponseEntity.ok(res);
    }


}
