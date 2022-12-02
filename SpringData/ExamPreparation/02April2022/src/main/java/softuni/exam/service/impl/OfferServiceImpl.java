package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.offerDtos.OfferImportDTO;
import softuni.exam.models.dto.offerDtos.OfferImportRootDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.enums.ApartmentType;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.AgentService;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final Path IMPORT_OFFERS_XML =
            Path.of("src", "main", "resources", "files", "xml", "offers.xml");

    private final OfferRepository offerRepository;
    private final AgentService agentService;
    private final ApartmentService apartmentService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, AgentService agentService, ApartmentService apartmentService, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.offerRepository = offerRepository;
        this.agentService = agentService;
        this.apartmentService = apartmentService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(IMPORT_OFFERS_XML);
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        OfferImportRootDTO offerImportRootDTO =
                xmlParser.fromFile(IMPORT_OFFERS_XML.toString(), OfferImportRootDTO.class);

        return offerImportRootDTO.getOffers().stream()
                .map(this::importOffer).collect(Collectors.joining(System.lineSeparator()));
    }

    private String importOffer(OfferImportDTO offerImportDTO) {
        Set<ConstraintViolation<OfferImportDTO>> violations = validationUtil.violation(offerImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid offer";
        }

        Optional<Agent> searchAgent = agentService.findByName(offerImportDTO.getAgent().getName());

        if (searchAgent.isEmpty()) {
            return "Invalid offer";
        }

        Apartment apartmentById = apartmentService.findById(offerImportDTO.getApartment().getId());

        Offer offer = modelMapper.map(offerImportDTO, Offer.class);
        offer.setAgent(searchAgent.get());
        offer.setApartment(apartmentById);
        this.offerRepository.save(offer);

        return String.format("Successfully imported offer %.2f", offer.getPrice());
    }

    @Override
    public String exportOffers() {
        List<Offer> offers =
                this.offerRepository.findByApartmentApartmentTypeOrderByApartmentAreaDescPriceAsc
                        (ApartmentType.three_rooms);

        return offers.stream().map(Offer::toString).collect(Collectors.joining(System.lineSeparator()));
    }
}
