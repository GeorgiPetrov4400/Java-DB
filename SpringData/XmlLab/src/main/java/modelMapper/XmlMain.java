package modelMapper;

import modelMapper.entities.dtos.addresses.AddressContextDTO;
import modelMapper.entities.dtos.addresses.AddressXmlDTO;
import modelMapper.entities.dtos.addresses.CountryContextDTO;
import modelMapper.entities.dtos.addresses.CountryXmlDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@Component
public class XmlMain implements CommandLineRunner {

    //  @Qualifier("addressContext") // if names are different with class Config
    private final JAXBContext addressContext;
    //  @Qualifier("countryContext") // if names are different with class Config
    private final JAXBContext countryContext;

    public XmlMain(JAXBContext addressContext, JAXBContext countryContext) {
        this.addressContext = addressContext;
        this.countryContext = countryContext;
    }

    @Override
    public void run(String... args) throws Exception {
        //   XmlMarshaller();

        // INPUT UNMARSHALLER
        // <?xml version="1.0" encoding="UTF-8" standalone="yes"?><address-xml><id>10</id><countries><countries value="Bulgaria"/><countries value="Bulgaria"/><countries value="Bulgaria"/></countries><city>Burgas</city></address-xml>
        //   press ctrl + d to end inputStream

        //   XmlUnmarshaller();

        CountryContextDTO countryContextDTO = new CountryContextDTO("Bulgaria");
        AddressContextDTO addressContextDTO = new AddressContextDTO(10, "Bulgaria", "Vidin");

        Marshaller countryMarshall = countryContext.createMarshaller();
        countryMarshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        countryMarshall.marshal(countryContextDTO, System.out);

        Marshaller addressMarshall = addressContext.createMarshaller();
        addressMarshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        addressMarshall.marshal(addressContextDTO, System.out);

    }

    private static void XmlUnmarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(AddressXmlDTO.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        AddressXmlDTO unmarshal = (AddressXmlDTO) unmarshaller.unmarshal(System.in);

        System.out.println(unmarshal);
    }

    private static void XmlMarshaller() throws JAXBException {
        CountryXmlDTO countryXmlDTO = new CountryXmlDTO("Bulgaria");
        AddressXmlDTO addressXmlDTO = new AddressXmlDTO(10, countryXmlDTO, "Burgas");

        JAXBContext context = JAXBContext.newInstance(AddressXmlDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // Pretty printing for xml

        marshaller.marshal(addressXmlDTO, System.out);
    }
}
