package poliklinika.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import poliklinika.model.Record;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoRecordDto {

    private Date date;
    private String patientUsername;
    private String serviceName;

}
