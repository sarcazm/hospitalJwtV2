package poliklinika.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Data
public class Doctor  extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username")
    private String username;


    /*@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "doctor_roles", joinColumns = @JoinColumn(name = "doctor_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;*/

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "doctor_specialties",
            joinColumns = {@JoinColumn(name = "doctor_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "specialty_id", referencedColumnName = "id")})
    private Specialty specialty;*/
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @OneToMany(mappedBy = "doctorId", fetch = FetchType.LAZY)
    private List<Record> records = new ArrayList<>();
}
