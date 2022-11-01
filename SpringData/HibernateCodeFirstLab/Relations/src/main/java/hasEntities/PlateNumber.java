package hasEntities;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "plate_number")
public class PlateNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String number;

    @OneToOne(targetEntity = SportCar.class, mappedBy = "plateNumber")
    private SportCar sportCar;

    public PlateNumber() {
    }

    public PlateNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
