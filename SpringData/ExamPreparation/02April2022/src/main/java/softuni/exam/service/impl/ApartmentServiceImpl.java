package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.apartmentDtos.ApartmentImportDTO;
import softuni.exam.models.dto.apartmentDtos.ApartmentImportRootDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final Path IMPORT_APARTMENTS_XML =
            Path.of("src", "main", "resources", "files", "xml", "apartments.xml");

    private final ApartmentRepository apartmentRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownService townService, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.apartmentRepository = apartmentRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(IMPORT_APARTMENTS_XML);
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        ApartmentImportRootDTO apartmentImportRootDTO =
                xmlParser.fromFile(IMPORT_APARTMENTS_XML.toString(), ApartmentImportRootDTO.class);

        return apartmentImportRootDTO.getApartments().stream()
                .map(this::importApartment).collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public Apartment findById(Long id) {
        return this.apartmentRepository.findById(id).orElse(null);
    }

    private String importApartment(ApartmentImportDTO apartmentImportDTO) {
        Set<ConstraintViolation<ApartmentImportDTO>> violations = validationUtil.violation(apartmentImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid apartment";
        }

        Optional<Apartment> apartmentsByAreaAndTown =
                apartmentRepository.findByTownTownNameAndArea(apartmentImportDTO.getTownName(),
                        apartmentImportDTO.getArea());

        Optional<Town> townOptional = townService.findByTownName(apartmentImportDTO.getTownName());

        if (apartmentsByAreaAndTown.isPresent() || townOptional.isEmpty()) {
            return "Invalid apartment";
        }

        Apartment apartment = modelMapper.map(apartmentImportDTO, Apartment.class);
        apartment.setTown(townOptional.get());

        this.apartmentRepository.save(apartment);
        return String.format("Successfully imported apartment %s - %.2f",
                apartment.getApartmentType(), apartment.getArea());
    }
}
