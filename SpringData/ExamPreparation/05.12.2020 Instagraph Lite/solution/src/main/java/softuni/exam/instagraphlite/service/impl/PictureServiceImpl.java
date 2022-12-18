package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.pictureDtos.PictureImportDTO;
import softuni.exam.instagraphlite.models.dtos.pictureDtos.PicturePathNameDTO;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtil;


import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private static final Path IMPORT_PICTURES_JSON =
            Path.of("src", "main", "resources", "files", "pictures.json");

    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(IMPORT_PICTURES_JSON);
    }

    @Override
    public String importPictures() throws IOException {
        PictureImportDTO[] pictureImportDTOS =
                gson.fromJson(readFromFileContent(), PictureImportDTO[].class);

        return Arrays.stream(pictureImportDTOS)
                .map(this::importPicture)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importPicture(PictureImportDTO pictureImportDTO) {
        Set<ConstraintViolation<PictureImportDTO>> violations = validationUtil.violation(pictureImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid picture";
        }

        Optional<Picture> findByPath = this.pictureRepository.findByPath(pictureImportDTO.getPath());

        if (findByPath.isPresent()) {
            return "Invalid picture";
        }

        Picture picture = modelMapper.map(pictureImportDTO, Picture.class);
        this.pictureRepository.save(picture);
        return String.format("Successfully imported Picture, with size %.2f", picture.getSize());
    }

    @Override
    public String exportPictures() {
        return null;
    }

    @Override
    public Optional<Picture> findPictureByPath(PicturePathNameDTO path) {
        return this.pictureRepository.findByPath(path.getPath());
    }
}
