package softuni.exam.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    //The combination of make, model and kilometers makes a car unique.
    @Column
    private String make;

    @Column
    private String model;

    @Column
    private int kilometers;

    @Column(name = "registered_on")
    private LocalDate registeredOn;

    @OneToMany(targetEntity = Picture.class, mappedBy = "car")
    private Set<Picture> pictures;

//    @OneToMany
//    private List<Offer> offers;

    public Car() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return String.format("Car make - %s, model - %s%n" +
                        "\tKilometers - %d%n" +
                        "\tRegistered on - %s%n" +
                        "\tNumber of pictures - %s",
                getMake(), getModel(), getKilometers(), getRegisteredOn(), getPictures().size());
    }
}
