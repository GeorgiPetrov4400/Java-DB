package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class PassengerVehicle extends Vehicle {

    @Basic
    protected Integer seats;

    public PassengerVehicle(String type) {
        super(type);
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
