package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.mechanicDtos.MechanicImportDTO;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;
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
public class MechanicServiceImpl implements MechanicService {

    private static final Path IMPORT_MECHANICS_JSON =
            Path.of("src", "main", "resources", "files", "json", "mechanics.json");

    private final MechanicRepository mechanicRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public MechanicServiceImpl(MechanicRepository mechanicRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.mechanicRepository = mechanicRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.mechanicRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(IMPORT_MECHANICS_JSON);
    }

    @Override
    public String importMechanics() throws IOException {
        MechanicImportDTO[] mechanicImportDTOS =
                gson.fromJson(readMechanicsFromFile(), MechanicImportDTO[].class);

        return Arrays.stream(mechanicImportDTOS)
                .map(this::importMechanic).collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public Optional<Mechanic> findByFirstName(String firstName) {
        return this.mechanicRepository.findByFirstName(firstName);
    }

    private String importMechanic(MechanicImportDTO mechanicImportDTO) {
        Set<ConstraintViolation<MechanicImportDTO>> violations = validationUtil.violation(mechanicImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid mechanic";
        }

        Optional<Mechanic> findByEmail = this.mechanicRepository.findByEmail(mechanicImportDTO.getEmail());

        Optional<Mechanic> findByPhone = this.mechanicRepository.findByPhone(mechanicImportDTO.getPhone());

        if (findByEmail.isPresent() || findByPhone.isPresent()) {
            return "Invalid mechanic";
        }

        Mechanic mechanic = modelMapper.map(mechanicImportDTO, Mechanic.class);
        this.mechanicRepository.save(mechanic);
        return String.format("Successfully imported mechanic %s %s",
                mechanic.getFirstName(), mechanic.getLastName());
    }
}
