package HospitalDatabase.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(unique = true)
    private String email;

    @Column(name = "birthday_date", nullable = false)
    private LocalDate birthDate;

    private String picture;

    @Column(name = "has_medical_insurance")
    private boolean hasMedicalInsurance;

    @OneToMany(targetEntity = Visitation.class, mappedBy = "patient")
    private Set<Visitation> visitations;

    @ManyToMany
    @JoinTable(
            name = "patients_diagnoses",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    )
    private Set<Diagnose> diagnoses;

    @ManyToMany
    @JoinTable(
            name = "patients_medicaments",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id")

    )
    private Set<Medicament> medicaments;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String address, String email, LocalDate birthDate, String picture, boolean hasMedicalInsurance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.birthDate = birthDate;
        this.picture = picture;
        this.hasMedicalInsurance = hasMedicalInsurance;
        this.visitations = new HashSet<>();
        this.diagnoses = new HashSet<>();
        this.medicaments = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return birthDate;
    }

    public void setDate(LocalDate birthdayDate) {
        this.birthDate = birthdayDate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isHasMedicalInsurance() {
        return hasMedicalInsurance;
    }

    public void setHasMedicalInsurance(boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }
}
