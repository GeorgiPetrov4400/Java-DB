package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.agentDtos.AgentImportDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.service.AgentService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements AgentService {

    private static final Path IMPORT_AGENTS_JSON =
            Path.of("src", "main", "resources", "files", "json", "agents.json");

    private final AgentRepository agentRepository;
    private final TownService townService;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, TownService townService, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.agentRepository = agentRepository;
        this.townService = townService;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(IMPORT_AGENTS_JSON);
    }

    @Override
    public String importAgents() throws IOException {
        AgentImportDTO[] agentImportDTOS = gson.fromJson(readAgentsFromFile(), AgentImportDTO[].class);

        return Arrays.stream(agentImportDTOS).map(this::importAgent)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public Optional<Agent> findByName(String name) {
        return this.agentRepository.findByFirstName(name);
    }

    private String importAgent(AgentImportDTO agentImportDTO) {
        Set<ConstraintViolation<AgentImportDTO>> violations = validationUtil.violation(agentImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid agent";
        }

        Optional<Agent> searchAgentByFirtName =
                this.agentRepository.findByFirstName(agentImportDTO.getFirstName());

        Optional<Town> searchTown = this.townService.findByTownName(agentImportDTO.getTown());

        if (searchAgentByFirtName.isPresent() || searchTown.isEmpty()) {
            return "Invalid agent";
        }


        Agent agent = modelMapper.map(agentImportDTO, Agent.class);
        agent.setTown(searchTown.get());
        this.agentRepository.save(agent);

        return String.format("Successfully imported agent - %s %s", agent.getFirstName(), agent.getLastName());
    }
}
