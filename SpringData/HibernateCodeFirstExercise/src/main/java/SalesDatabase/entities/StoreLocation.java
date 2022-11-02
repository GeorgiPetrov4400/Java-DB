package SalesDatabase.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "store_locations")
public class StoreLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "location_name")
    private String locationName;

    @OneToMany(targetEntity = Sale.class, mappedBy = "storeLocation")
    private Set<Sale> sales;

    public StoreLocation() {}

    public StoreLocation(String locationName) {
        this.locationName = locationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
