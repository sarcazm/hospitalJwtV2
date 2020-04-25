package poliklinika.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "patients")
@Data

public class Patient extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "snils")
    private String snils;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "patinetId", fetch = FetchType.EAGER)
    private List<Record> records = new ArrayList<>();

    /*@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "patient_roles", joinColumns = @JoinColumn(name = "patient_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;*/
}
