package modelMapper.entities.dtos.addresses;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "address-xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressXmlDTO {

    @XmlElement
    private long id;

    // @XmlElement
    // private String country;
    //  private CountryXmlDTO country;
    @XmlElementWrapper
    List<CountryXmlDTO> countries;
    @XmlElement
    private String city;

    public AddressXmlDTO() {
    }

    public AddressXmlDTO(long id, CountryXmlDTO country, String city) {
        this.id = id;
        //  this.country = country;
        this.countries = List.of(country, country, country);
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<CountryXmlDTO> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryXmlDTO> countries) {
        this.countries = countries;
    }

    //    public CountryXmlDTO getCountry() {
//        return country;
//    }
//
//    public void setCountry(CountryXmlDTO country) {
//        this.country = country;
//    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "AddressXmlDTO{" +
                "id=" + id +
                ", countries=" + countries +
                ", city='" + city + '\'' +
                '}';
    }
}
