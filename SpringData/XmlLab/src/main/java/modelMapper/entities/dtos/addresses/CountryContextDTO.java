package modelMapper.entities.dtos.addresses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountryContextDTO {

    @XmlAttribute
    private String value;

    public CountryContextDTO() {
    }

    public CountryContextDTO(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CountryContextDTO{" +
                "value='" + value + '\'' +
                '}';
    }
}
