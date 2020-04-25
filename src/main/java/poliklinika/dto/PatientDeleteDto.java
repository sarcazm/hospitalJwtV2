package poliklinika.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import poliklinika.model.Patient;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDeleteDto {
    private String username;

}
