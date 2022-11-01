package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bikes")
public class Bike extends Vehicle {
    private static final String TYPE = "Bike";
    public Bike() {
        super(TYPE);
    }
}
