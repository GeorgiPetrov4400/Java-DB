package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {
    private static final String TYPE = "Car";
    @Basic
    private Integer seats;

    public Car() {
        super(TYPE);
    }

    public Car(String model,String fuelType ,Integer seats) {
        this();
        this.model = model;
        this.fuelType = fuelType;
        this.seats = seats;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
