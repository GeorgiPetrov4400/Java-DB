package softuni.exam.models.dto.townDtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TownImportDTO {

    @Size(min = 2)
    @NotNull
    private String townName;

    @Min(1)
    @NotNull
    private Integer population;

    public TownImportDTO() {
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
}
