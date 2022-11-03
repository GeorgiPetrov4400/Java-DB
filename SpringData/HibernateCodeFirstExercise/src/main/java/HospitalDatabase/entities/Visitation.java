package HospitalDatabase.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
@Table(name = "visitations")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "diagnose_id")
    private Diagnose diagnose;

    @ManyToMany
    @JoinTable(
            name = "visitations_medicaments",
            joinColumns = @JoinColumn(name = "visitation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id")
    )
    private Set<Medicament> medicaments;

    public Visitation() {
    }

    public Visitation(LocalDate date, String comments) {
        this.date = date;
        this.comments = comments;
        this.medicaments = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
