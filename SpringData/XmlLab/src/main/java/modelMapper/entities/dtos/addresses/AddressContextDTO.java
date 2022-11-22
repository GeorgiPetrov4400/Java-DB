package modelMapper.entities.dtos.addresses;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressContextDTO {

    @XmlAttribute
    private int id;
    @XmlElement
    private String country;
    @XmlElement
    private String city;

    public AddressContextDTO() {
    }

    public AddressContextDTO(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    @Override
    public String toString() {
        return "AddressContextDTO{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
