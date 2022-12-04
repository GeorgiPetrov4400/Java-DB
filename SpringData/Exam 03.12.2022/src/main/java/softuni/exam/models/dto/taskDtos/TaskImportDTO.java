package softuni.exam.models.dto.taskDtos;

import softuni.exam.models.dto.carDtos.CarIdDTO;
import softuni.exam.models.dto.mechanicDtos.MechanicFirstNameDTO;
import softuni.exam.models.dto.partDtos.PartIdDTO;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskImportDTO {

    @XmlElement(name = "date")
    @NotNull
    private String date;

    @XmlElement(name = "price")
    @DecimalMin(value = "1")
    @NotNull
    private BigDecimal price;

    @XmlElement(name = "car")
    private CarIdDTO car;

    @XmlElement(name = "mechanic")
    private MechanicFirstNameDTO mechanic;

    @XmlElement(name = "part")
    private PartIdDTO part;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CarIdDTO getCar() {
        return car;
    }

    public void setCar(CarIdDTO car) {
        this.car = car;
    }

    public MechanicFirstNameDTO getMechanic() {
        return mechanic;
    }

    public void setMechanic(MechanicFirstNameDTO mechanic) {
        this.mechanic = mechanic;
    }

    public PartIdDTO getPart() {
        return part;
    }

    public void setPart(PartIdDTO part) {
        this.part = part;
    }
}
