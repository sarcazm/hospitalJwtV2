package poliklinika.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import poliklinika.model.Record;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordDto {

    private Long id;
    private Date date;
    //private Patient patinetId;
    //private Doctor doctorId;
    //private Service serviceId;
    private String status;

    private String patientLastName;
    private String patientFirstName;
    private String doctorLastName;
    private String doctorFirstName;
    private String serviceName;

    /*public Record toRecord(){
        Record record = new Record();
        record.setId(id);
        record.setDate(date);
        record.setPatinetId(patinetId);
        record.setDoctorId(doctorId);
        record.setServiceId(serviceId);
        record.setStatus(Status.valueOf(status));
        return record;
    }*/

    public static RecordDto fromRecord(Record record){
        RecordDto recordDto = new RecordDto();
        recordDto.setId(record.getId());
        recordDto.setDate(record.getDate());
        //пробую так
        recordDto.setPatientFirstName(record.getPatinetId().getFirstName());
        recordDto.setPatientLastName(record.getPatinetId().getLastName());
        recordDto.setDoctorFirstName(record.getDoctorId().getFirstName());
        recordDto.setDoctorLastName(record.getDoctorId().getLastName());
        recordDto.setServiceName(record.getServiceId().getName());
        //recordDto.setPatinetId(record.getPatinetId());
        //recordDto.setDoctorId(record.getDoctorId());
        //recordDto.setServiceId(record.getServiceId());
        recordDto.setStatus(record.getStatus().name());
        return recordDto;
    }

    /*@Override
    public String toString() {
        return "RecordDto{" +
                "id=" + id +
                ", date=" + date +
                ", patinet=" + patinetId.getLastName() +
                ", doctor=" + doctorId.getLastName() +
                ", serviceId=" + serviceId.getName() +
                ", status='" + status + '\'' +
                '}';
    }*/
}

