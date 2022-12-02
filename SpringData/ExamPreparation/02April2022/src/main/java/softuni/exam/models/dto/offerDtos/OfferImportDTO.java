package softuni.exam.models.dto.offerDtos;

import softuni.exam.models.dto.agentDtos.AgentNameDTO;
import softuni.exam.models.dto.apartmentDtos.ApartmentNameDTO;

import javax.validation.constraints.DecimalMin;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferImportDTO {

    @XmlElement(name = "price")
    @DecimalMin(value = "1")
    private BigDecimal price;

    @XmlElement(name = "publishedOn")
    private String publishedOn;

    @XmlElement(name = "apartment")
    private ApartmentNameDTO apartment;

    @XmlElement(name = "agent")
    private AgentNameDTO agent;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }

    public ApartmentNameDTO getApartment() {
        return apartment;
    }

    public void setApartment(ApartmentNameDTO apartment) {
        this.apartment = apartment;
    }

    public AgentNameDTO getAgent() {
        return agent;
    }

    public void setAgent(AgentNameDTO agent) {
        this.agent = agent;
    }
}
