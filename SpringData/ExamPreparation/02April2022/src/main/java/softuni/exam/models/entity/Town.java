package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    @Column(name = "town_name", unique = true, nullable = false)
    private String townName;

    @Column(nullable = false)
    @Positive
    private Integer population;

    @OneToMany(targetEntity = Agent.class, mappedBy = "town")
    private Set<Agent> agents;

    @OneToMany(targetEntity = Apartment.class, mappedBy = "town")
    private Set<Apartment> apartments;

    public Town() {
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Set<Agent> getAgents() {
        return agents;
    }

    public void setAgents(Set<Agent> agents) {
        this.agents = agents;
    }

    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }
}
