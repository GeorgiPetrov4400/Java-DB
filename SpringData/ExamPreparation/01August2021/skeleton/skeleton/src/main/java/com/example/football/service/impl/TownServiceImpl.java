package com.example.football.service.impl;

import com.example.football.models.dto.townDtos.TownImportDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "towns.json");

        return Files.readString(path);
    }

    @Override
    public String importTowns() throws IOException {
        String json = readTownsFileContent();

        TownImportDTO[] townImportDTOS = this.gson.fromJson(json, TownImportDTO[].class);

        List<String> townResult = new ArrayList<>();
        for (TownImportDTO townImportDTO : townImportDTOS) {
            Set<ConstraintViolation<TownImportDTO>> violations = validationUtil.violation(townImportDTO);

            if (violations.isEmpty()) {
                Optional<Town> findTownByName = this.townRepository.findByName(townImportDTO.getName());

                if (findTownByName.isEmpty()) {
                    Town town = this.modelMapper.map(townImportDTO, Town.class);
                    this.townRepository.save(town);

                    String addTownMessage = String.format("Successfully imported %s - %d",
                            townImportDTO.getName(), townImportDTO.getPopulation());
                    townResult.add(addTownMessage);
                } else {
                    townResult.add("Invalid town");
                }
            } else {
                townResult.add("Invalid town");
            }
        }
        return String.join("\n", townResult);
    }
}
