package com.example.football.service.impl;

import com.example.football.models.dto.teamDtos.TeamImportDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "teams.json");

        return Files.readString(path);
    }

    @Override
    public String importTeams() throws IOException {
        String json = readTeamsFileContent();

        TeamImportDTO[] teamImportDTOS = this.gson.fromJson(json, TeamImportDTO[].class);

        return Arrays.stream(teamImportDTOS).map(this::importTeam).collect(Collectors.joining("\n"));
    }

    private String importTeam(TeamImportDTO teamImportDTO) {
        Set<ConstraintViolation<TeamImportDTO>> violations = validationUtil.violation(teamImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid team";
        }

        Optional<Team> teamByName = this.teamRepository.findByName(teamImportDTO.getName());
        if (teamByName.isPresent()) {
            return "Invalid team";
        }

        Team team = modelMapper.map(teamImportDTO, Team.class);
        Optional<Town> townByName = this.townRepository.findByName(teamImportDTO.getTownName());
        team.setTown(townByName.get());

        this.teamRepository.save(team);
        return "Successfully imported Team " + team;
    }
}
