package poliklinika.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import poliklinika.model.Patient;
import poliklinika.model.Status;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDto {

    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String snils;


    public Patient toPatient(){
        Patient patient = new Patient();

        patient.setUsername(username);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPhone(phone);
        patient.setSnils(snils);

        return patient;
    }

    public static PatientDto fromPatient(Patient patient){
        PatientDto patientDto = new PatientDto();

        patientDto.setUsername(patient.getUsername());
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setPhone(patient.getPhone());
        patientDto.setSnils(patient.getSnils());

        return patientDto;
    }


}
