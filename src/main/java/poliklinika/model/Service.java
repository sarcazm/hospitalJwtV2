package poliklinika.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
@Data
public class Service  extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "serviceId", fetch = FetchType.LAZY)
    private List<Record> records = new ArrayList<>();
}
