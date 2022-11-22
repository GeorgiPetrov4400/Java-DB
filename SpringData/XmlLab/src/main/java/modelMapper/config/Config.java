package modelMapper.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelMapper.entities.dtos.addresses.AddressContextDTO;
import modelMapper.entities.dtos.addresses.CountryContextDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Scanner;

@Configuration
public class Config {

    @Bean
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Scanner createScanner() {
        return new Scanner(System.in);
    }

    @Bean
    public Gson createGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    @Bean("countryContext")
    public JAXBContext createCountryContext() throws JAXBException {
        return JAXBContext.newInstance(CountryContextDTO.class);
    }

    @Bean("addressContext")
    public JAXBContext createAddressContext() throws JAXBException {
        return JAXBContext.newInstance(AddressContextDTO.class);
    }
}
