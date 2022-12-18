package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.userDtos.UserImportDTO;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
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
public class UserServiceImpl implements UserService {

    private static final Path IMPORT_USERS_JSON =
            Path.of("src", "main", "resources", "files", "users.json");

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final PictureService pictureService;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, PictureService pictureService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.pictureService = pictureService;
    }

    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(IMPORT_USERS_JSON);
    }

    @Override
    public String importUsers() throws IOException {
        UserImportDTO[] userImportDTOS = gson.fromJson(readFromFileContent(), UserImportDTO[].class);

        return Arrays.stream(userImportDTOS)
                .map(this::importUser)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importUser(UserImportDTO userImportDTO) {
        Set<ConstraintViolation<UserImportDTO>> violations = validationUtil.violation(userImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid user";
        }

        Optional<User> byUsername = this.userRepository.findByUsername(userImportDTO.getUsername());

//        Optional<Picture> pictureByPath =
//                this.pictureService.findPictureByPath(userImportDTO.getProfilePicture().getPath());
        Optional<Picture> pictureByPath = this.pictureService.findPictureByPath(userImportDTO.getProfilePicture());

        if (byUsername.isPresent() || pictureByPath.isEmpty()) {
            return "Invalid user";
        }

        User user = modelMapper.map(userImportDTO, User.class);
        this.userRepository.save(user);
        return String.format("Successfully imported User: %s", userImportDTO.getUsername());
    }

    @Override
    public String exportUsersWithTheirPosts() {
        return null;
    }
}
