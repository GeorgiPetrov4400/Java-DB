package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.sellerDtos.SellerImportWrapperDTO;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SellerServiceImpl implements SellerService {

    private final Path SELLERS_IMPORT_XML =
            Path.of("src", "main", "resources", "files", "xml", "sellers.xml");

    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(SELLERS_IMPORT_XML);
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder builder = new StringBuilder();

        SellerImportWrapperDTO sellerImportWrapperDTO =
                xmlParser.fromFile(SELLERS_IMPORT_XML.toString(), SellerImportWrapperDTO.class);

        sellerImportWrapperDTO.getSellers().stream()
                .filter(sellerImportDTO -> {
                    boolean isValid = validationUtil.isValid(sellerImportDTO);
                    builder.append(isValid ? String.format("Successfully imported seller %s - %s",
                                    sellerImportDTO.getLastName(), sellerImportDTO.getEmail()) :
                                    "Invalid seller")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(sellerImportDTO -> modelMapper.map(sellerImportDTO, Seller.class))
                .forEach(sellerRepository::save);

        return builder.toString();
    }

    @Override
    public Seller findById(Long id) {
        return this.sellerRepository.findById(id).orElse(null);
    }
}
