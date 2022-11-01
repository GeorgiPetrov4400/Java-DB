package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Truck extends Vehicle {
    private static final String TYPE = "Truck";

    private Double loadCapacity;

    public Truck() {
        super(TYPE);
    }

    public Truck(String model, String fuelType, Double loadCapacity) {
        this();
        this.model = model;
        this.fuelType = fuelType;
        this.loadCapacity = loadCapacity;
    }

    public Double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(Double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
