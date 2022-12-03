package exam.service.impl;

import exam.model.dto.shopDto.ShopImportDTO;
import exam.model.dto.shopDto.ShopImportRootDTO;
import exam.model.entity.Shop;
import exam.model.entity.Town;
import exam.repository.ShopRepository;
import exam.service.ShopService;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private static final Path IMPORT_SHOPS_XML =
            Path.of("src", "main", "resources", "files", "xml", "shops.xml");

    private final ShopRepository shopRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, TownService townService, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.shopRepository = shopRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(IMPORT_SHOPS_XML);
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        ShopImportRootDTO shops = xmlParser.fromFile(IMPORT_SHOPS_XML.toString(), ShopImportRootDTO.class);

        return shops.getShops().stream().map(this::importShop).collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public Shop findByName(String name) {
        return this.shopRepository.findByName(name).orElse(null);
    }

    private String importShop(ShopImportDTO shopImportDTO) {
        Set<ConstraintViolation<ShopImportDTO>> violations = validationUtil.violation(shopImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid shop";
        }

        Optional<Shop> findShopByName = this.shopRepository.findByName(shopImportDTO.getName());

        if (findShopByName.isPresent()) {
            return "Invalid shop";
        }

        Shop shop = modelMapper.map(shopImportDTO, Shop.class);

        Town town = this.townService.findByName(shopImportDTO.getTown().getName());
        shop.setTown(town);
        this.shopRepository.save(shop);

        return String.format("Successfully imported Shop %s - %.0f", shop.getName(), shop.getIncome());
    }
}
