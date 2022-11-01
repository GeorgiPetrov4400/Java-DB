package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Plane extends Vehicle {
    private static final String TYPE = "Plane";

    private Integer passengerCapacity;

    public Plane() {
        super(TYPE);
    }

    public Plane(String model, String fuelType, Integer passengerCapacity) {
        this();
        this.model = model;
        this.fuelType = fuelType;
        this.passengerCapacity = passengerCapacity;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}
