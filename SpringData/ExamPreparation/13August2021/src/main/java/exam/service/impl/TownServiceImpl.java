package exam.service.impl;

import exam.model.dto.townDto.TownImportDTO;
import exam.model.dto.townDto.TownImportRootDTO;
import exam.model.entity.Town;
import exam.repository.TownRepository;
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
public class TownServiceImpl implements TownService {

    private static final Path IMPORT_TOWNS_XML =
            Path.of("src", "main", "resources", "files", "xml", "towns.xml");

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(IMPORT_TOWNS_XML);
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        TownImportRootDTO towns = xmlParser.fromFile(IMPORT_TOWNS_XML.toString(), TownImportRootDTO.class);

        return towns.getTowns().stream().map(this::importTown).collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public Town findByName(String name) {
        return this.townRepository.findTownByName(name).orElse(null);
    }

    private String importTown(TownImportDTO townImportDTO) {
        Set<ConstraintViolation<TownImportDTO>> violations = validationUtil.violation(townImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid town";
        }

        Optional<Town> townByName = this.townRepository.findTownByName(townImportDTO.getName());

        if (townByName.isPresent()) {
            return "Invalid town";
        }

        Town town = modelMapper.map(townImportDTO, Town.class);
        this.townRepository.save(town);

        return String.format("Successfully imported Town %s", town.getName());
    }
}
