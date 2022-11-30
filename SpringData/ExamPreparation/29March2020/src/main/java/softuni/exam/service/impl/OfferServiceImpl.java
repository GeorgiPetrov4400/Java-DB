package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.offerDtos.OfferImportWrapperDTO;
import softuni.exam.models.entities.Offer;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.OfferService;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OfferServiceImpl implements OfferService {

    private final Path OFFERS_IMPORT_XML =
            Path.of("src", "main", "resources", "files", "xml", "offers.xml");

    private final OfferRepository offerRepository;
    private final CarService carService;
    private final SellerService sellerService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, CarService carService, SellerService sellerService, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.offerRepository = offerRepository;
        this.carService = carService;
        this.sellerService = sellerService;
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
        return Files.readString(OFFERS_IMPORT_XML);
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder builder = new StringBuilder();

        OfferImportWrapperDTO offerImportWrapperDTO =
                xmlParser.fromFile(OFFERS_IMPORT_XML.toString(), OfferImportWrapperDTO.class);

        offerImportWrapperDTO.getOffers().stream()
                .filter(offerImportDTO -> {
                    boolean isValid = validationUtil.isValid(offerImportDTO);
                    builder.append(isValid ? String.format("Successfully import offer %s - %s",
                                    offerImportDTO.getAddedOn(), offerImportDTO.isHasGoldStatus()) :
                                    "Invalid offer")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(offerImportDTO -> {
                    Offer offer = modelMapper.map(offerImportDTO, Offer.class);
                    offer.setCar(carService.findById(offerImportDTO.getCar().getId()));
                    offer.setSeller(sellerService.findById(offerImportDTO.getSeller().getId()));
                    return offer;
                })
                .forEach(offerRepository::save);

        return builder.toString();
    }
}
