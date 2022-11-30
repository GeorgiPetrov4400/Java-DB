package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.pictureDtos.PictureImportDTO;
import softuni.exam.models.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PictureServiceImpl implements PictureService {

    private final Path IMPORT_PICTURES_JSON =
            Path.of("src", "main", "resources", "files", "json", "pictures.json");

    private final PictureRepository pictureRepository;
    private final CarService carService;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, CarService carService, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.pictureRepository = pictureRepository;
        this.carService = carService;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(IMPORT_PICTURES_JSON);
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder builder = new StringBuilder();

        String json = readPicturesFromFile();

        PictureImportDTO[] pictureImportDTOS = gson.fromJson(json, PictureImportDTO[].class);

        Arrays.stream(pictureImportDTOS).filter(pictureImportDTO -> {
                    boolean isValid = validationUtil.isValid(pictureImportDTO);
                    builder.append(isValid ? String.format("Successfully imported picture - %s",
                                    pictureImportDTO.getName()) : "Invalid picture")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(pictureImportDTO -> {
                    Picture picture = modelMapper.map(pictureImportDTO, Picture.class);
                    picture.setCar(carService.findById(pictureImportDTO.getCar()));
                    return picture;
                })
                .forEach(pictureRepository::save);

        return builder.toString();
    }
}
