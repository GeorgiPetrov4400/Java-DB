package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends PassengerVehicle {
    private static final String TYPE = "Car";

    public Car() {
        super(TYPE);
    }

    public Car(String model,String fuelType ,Integer seats) {
        this();
        this.model = model;
        this.fuelType = fuelType;
        this.seats = seats;
    }

}
