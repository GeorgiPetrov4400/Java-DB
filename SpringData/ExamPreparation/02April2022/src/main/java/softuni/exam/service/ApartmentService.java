package softuni.exam.service;

import softuni.exam.models.entity.Apartment;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;


public interface ApartmentService {

    boolean areImported();

    String readApartmentsFromFile() throws IOException;

    String importApartments() throws IOException, JAXBException;

    Apartment findById(Long id);
}
