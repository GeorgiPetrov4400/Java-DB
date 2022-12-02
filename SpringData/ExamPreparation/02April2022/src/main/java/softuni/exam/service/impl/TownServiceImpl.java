package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.townDtos.TownImportDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

@Service
public class TownServiceImpl implements TownService {

    private static final Path IMPORT_TOWNS_JSON =
            Path.of("src", "main", "resources", "files", "json", "towns.json");
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(IMPORT_TOWNS_JSON);
    }

    @Override
    public String importTowns() throws IOException {
        TownImportDTO[] townImportDTOS = gson.fromJson(readTownsFileContent(), TownImportDTO[].class);

        List<String> townOutput = new ArrayList<>();

        for (TownImportDTO townImportDTO : townImportDTOS) {
            Set<ConstraintViolation<TownImportDTO>> violations = validationUtil.violation(townImportDTO);

            if (violations.isEmpty()) {
                Optional<Town> searchTown = this.townRepository.findByTownName(townImportDTO.getTownName());

                if (searchTown.isEmpty()) {
                    Town town = modelMapper.map(townImportDTO, Town.class);
                    this.townRepository.save(town);

                    townOutput.add(String.format("Successfully imported town %s - %d",
                            town.getTownName(), town.getPopulation()));
                } else {
                    townOutput.add("Invalid town");
                }
            } else {
                townOutput.add("Invalid town");
            }
        }

        return String.join(System.lineSeparator(), townOutput);
    }

    @Override
    public Optional<Town> findByTownName(String townName) {
        return this.townRepository.findByTownName(townName);
    }

//    @Override
//    public Town findTownByTownName(String townName) {
//        return this.townRepository.findByTownName(townName).orElse(null);
//    }

}
