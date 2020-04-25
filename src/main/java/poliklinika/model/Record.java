package poliklinika.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "records")
@Data

public class Record extends BaseEntity {

    @Column(name = "date")
    private Date date;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patinetId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctorId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private Service serviceId;

    @Override
    public String toString() {
        return "Record{" +
                "patinet=" + patinetId.getLastName() + " " + patinetId.getFirstName() + " записан к доктору" +
                ", doctor=" + doctorId.getLastName() + " " + doctorId.getFirstName() + " на " +
                "date=" + date +
                ", serviceId=" + serviceId.getName() +
                '}';
    }
}
