package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    private Mechanic mechanic;

    @ManyToOne
    private Part part;

    @ManyToOne
    private Car car;

    public Task() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return String.format("Car %s %s with %dkm%n" +
                        "-Mechanic: %s %s - task №%d:%n" +
                        "--Engine: %s%n" +
                        "---Price: %.2f$", getCar().getCarMake(), getCar().getCarModel(), getCar().getKilometers(),
                getMechanic().getFirstName(), getMechanic().getLastName(), getId(),
                getCar().getEngine(), getPrice());
    }
}
