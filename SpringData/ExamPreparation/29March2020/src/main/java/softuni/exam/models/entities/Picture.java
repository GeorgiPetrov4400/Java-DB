package softuni.exam.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Column(name = "date_and_time")
    private LocalDateTime dateAndTime;

    @ManyToOne
    private Car car;

//    @ManyToMany
//    private List<Offer> offers;

    public Picture() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}