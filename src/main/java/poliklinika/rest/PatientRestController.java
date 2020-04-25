package poliklinika.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poliklinika.dto.PatientDto;
import poliklinika.dto.RecordDto;
import poliklinika.model.Patient;
import poliklinika.model.Record;
import poliklinika.security.jwt.JwtTokenProvider;
import poliklinika.service.PatientService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/patient/")
public class PatientRestController {
    private final JwtTokenProvider jwtTokenProvider;

    private final PatientService patientService;

    @Autowired
    public PatientRestController(JwtTokenProvider jwtTokenProvider, PatientService patientService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.patientService = patientService;
    }

    @GetMapping("/info")
    public ResponseEntity<PatientDto> getInfoAboutThisPatient(HttpServletRequest req){

        Patient patient = patientService.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));

        if(patient == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        PatientDto result = PatientDto.fromPatient(patient);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all-records")
    public ResponseEntity<List<RecordDto>> getAllRecords(HttpServletRequest req){
        List<Record> records = patientService.getAllRecords(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        List<RecordDto> recordDtoList = records
                                                .stream()
                                                .map(record -> RecordDto.fromRecord(record))
                                                .collect(Collectors.toList());

        if(records == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(recordDtoList, HttpStatus.OK);
    }




}
