package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.partDtos.PartImportDTO;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.PartService;
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
public class PartServiceImpl implements PartService {

    private static final Path IMPORT_PARTS_JSON =
            Path.of("src", "main", "resources", "files", "json", "parts.json");

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.partRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files.readString(IMPORT_PARTS_JSON);
    }

    @Override
    public String importParts() throws IOException {
        PartImportDTO[] partImportDTOS =
                gson.fromJson(readPartsFileContent(), PartImportDTO[].class);

        return Arrays.stream(partImportDTOS)
                .map(this::importPart).collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public Part findById(Long id) {
        return this.partRepository.findById(id).orElse(null);
    }

    private String importPart(PartImportDTO partImportDTO) {
        Set<ConstraintViolation<PartImportDTO>> violations = validationUtil.violation(partImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid part";
        }

        Optional<Part> findByName = this.partRepository.findByPartName(partImportDTO.getPartName());

        if (findByName.isPresent()) {
            return "Invalid part";
        }

        Part part = modelMapper.map(partImportDTO, Part.class);
        this.partRepository.save(part);
        return String.format("Successfully imported part %s - %.2f",
                partImportDTO.getPartName(), partImportDTO.getPrice());
    }
}
