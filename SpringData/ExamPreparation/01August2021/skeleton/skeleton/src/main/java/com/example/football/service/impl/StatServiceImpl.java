package com.example.football.service.impl;

import com.example.football.models.dto.statDtos.StatImportDTO;
import com.example.football.models.dto.statDtos.StatImportWrapperDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {

    private final Path STATS_IMPORT_XML =
            Path.of("src", "main", "resources", "files", "xml", "stats.xml");

    private final StatRepository statRepository;
    private final Unmarshaller unmarshaller;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public StatServiceImpl(StatRepository statRepository, ModelMapper modelMapper, ValidationUtil validationUtil) throws JAXBException {
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;

        JAXBContext context = JAXBContext.newInstance(StatImportWrapperDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(STATS_IMPORT_XML);
    }

    @Override
    public String importStats() throws IOException, JAXBException {
        BufferedReader bufferedReader = Files.newBufferedReader(STATS_IMPORT_XML);
        StatImportWrapperDTO statImportWrapperDTO =
                (StatImportWrapperDTO) unmarshaller.unmarshal(bufferedReader);

        return statImportWrapperDTO.getStats().stream()
                .map(this::importStat).collect(Collectors.joining("\n"));
    }

    private String importStat(StatImportDTO statImportDTO) {
        Set<ConstraintViolation<StatImportDTO>> violations = validationUtil.violation(statImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid stat";
        }

        Optional<Stat> searchStat = this.statRepository.findByShootingAndPassingAndEndurance
                (statImportDTO.getShooting(), statImportDTO.getPassing(), statImportDTO.getEndurance());

        if (searchStat.isPresent()) {
            return "Invalid stat";
        }

        Stat stat = modelMapper.map(statImportDTO, Stat.class);
        this.statRepository.save(stat);
        return "Successfully imported Stat " + stat;
    }
}
