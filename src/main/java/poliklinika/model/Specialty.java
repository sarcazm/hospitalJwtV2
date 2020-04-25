package poliklinika.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "specialties")
@Data
public class Specialty extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "specialty", fetch = FetchType.EAGER)
    private List<Doctor> doctors;

    @Override
    public String toString() {
        return "Specialty{" +
                "id: " + super.getId() + ", " +
                "name: " + name + "}";
    }
}
