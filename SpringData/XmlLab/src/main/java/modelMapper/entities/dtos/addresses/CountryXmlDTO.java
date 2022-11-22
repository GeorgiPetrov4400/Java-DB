package modelMapper.entities.dtos.addresses;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountryXmlDTO {

    @XmlAttribute
    private String value;

    public CountryXmlDTO() {
    }

    public CountryXmlDTO(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CountryXmlDTO{" +
                "value='" + value + '\'' +
                '}';
    }
}
