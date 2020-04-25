package poliklinika.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poliklinika.dto.DoRecordDto;
import poliklinika.dto.PatientDeleteDto;
import poliklinika.dto.PatientDto;
import poliklinika.dto.RecordDto;
import poliklinika.model.Patient;
import poliklinika.model.Record;
import poliklinika.model.Status;
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
    @Autowired
    public DoctorRestController(JwtTokenProvider jwtTokenProvider, DoctorService doctorService, PatientService patientService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.doctorService = doctorService;
        this.patientService = patientService;
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

    @PostMapping("/delete")
    public ResponseEntity deletePatient(@RequestBody PatientDeleteDto patientDeleteDto){
        //почему то не удаляет из БД
        //Как лучше удалять? прям здесь? Или в DoctorsServiceImpl?
        boolean isDataBase = false;
        boolean deleteInDataBase = false;
        String yesOrNo = "no";
        Patient patient = patientService.findByUsername(patientDeleteDto.getUsername());
        patient.setStatus(Status.DELETED);
        if (patient != null){
            isDataBase = true;
            patientService.delete(patient.getId());
            patient = patientService.findByUsername(patientDeleteDto.getUsername());
            if (patient == null)deleteInDataBase = true;
        }
        System.out.println("Нашли? " + isDataBase);
        System.out.println("Удалили? " + deleteInDataBase);

        //этот метод не работает для удаления
        //doctorService.deletePatient(patientDeleteDto.getUsername());

        Map<Object, Object> res = new HashMap<>();
        res.put("patient", patientDeleteDto.getUsername());
        if (isDataBase && deleteInDataBase)yesOrNo = "yes";
        res.put("delete", yesOrNo);
        return ResponseEntity.ok(res);
    }


}
