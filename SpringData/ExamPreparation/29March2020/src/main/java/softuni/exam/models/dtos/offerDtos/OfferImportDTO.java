package softuni.exam.models.dtos.offerDtos;

import softuni.exam.models.dtos.carDtos.CarIdDTO;
import softuni.exam.models.dtos.sellerDtos.SellerIdDTO;

import javax.persistence.Column;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferImportDTO {

    @XmlElement
    @Size(min = 5)
    private String description;

    @XmlElement
    @Positive
    private BigDecimal price;

    @XmlElement(name = "added-on")
    private String addedOn;

    @XmlElement(name = "has-gold-status")
    private boolean hasGoldStatus;

    @XmlElement
    private CarIdDTO car;

    @XmlElement
    private SellerIdDTO seller;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public CarIdDTO getCar() {
        return car;
    }

    public void setCar(CarIdDTO car) {
        this.car = car;
    }

    public SellerIdDTO getSeller() {
        return seller;
    }

    public void setSeller(SellerIdDTO seller) {
        this.seller = seller;
    }
}
