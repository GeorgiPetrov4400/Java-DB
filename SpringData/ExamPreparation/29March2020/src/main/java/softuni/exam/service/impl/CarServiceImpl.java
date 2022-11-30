package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.carDtos.CarImportDTO;
import softuni.exam.models.entities.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final Path IMPORT_CARS_JSON =
            Path.of("src", "main", "resources", "files", "json", "cars.json");

    private final CarRepository carRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(IMPORT_CARS_JSON);
    }

    @Override
    public String importCars() throws IOException {
//        StringBuilder builder = new StringBuilder();
//
//        Arrays.stream(gson.fromJson(readCarsFileContent(), CarImportDTO[].class))
//                .filter(carImportDTO -> {
//                    boolean isValid = validationUtil.isValid(carImportDTO);
//                    builder.append(isValid ? String.format("Successfully imported car - %s - %s",
//                                    carImportDTO.getMake(), carImportDTO.getModel()) : "Invalid car")
//                            .append(System.lineSeparator());
//
//                    return isValid;
//                })
//                .map(carImportDTO -> modelMapper.map(carImportDTO, Car.class))
//                .forEach(carRepository::save);
//
//        return builder.toString();

        String json = readCarsFileContent();

        CarImportDTO[] carImportDTOS = this.gson.fromJson(json, CarImportDTO[].class);

        return Arrays.stream(carImportDTOS).map(this::importCar).collect(Collectors.joining("\n"));
    }

    private String importCar(CarImportDTO carImportDTO) {
        Set<ConstraintViolation<CarImportDTO>> violations = validationUtil.violation(carImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid car";
        }

        Car car = modelMapper.map(carImportDTO, Car.class);
        this.carRepository.save(car);
        return "Successfully imported car - " + car.getMake() + " - " + car.getModel();

    }

    @Override
    @Transactional
    public String getCarsOrderByPicturesCountThenByMake() {
//        StringBuilder builder = new StringBuilder();
//
//        List<Car> cars = carRepository.findAllOrderByPicturesCountDescMakeAsc();
//
//        cars.forEach(car -> {
//            builder.append(String.format("Car make - %s, model - %s%n" +
//                            "\tKilometers - %d%n" +
//                            "\tRegistered on - %s%n" +
//                            "\tNumber of pictures - %s",
//                    car.getMake(), car.getModel(), car.getKilometers(), car.getRegisteredOn(),
//                    car.getPictures().size()));
//            builder.append(System.lineSeparator());
//        });
//
//        return builder.toString();

        List<Car> cars = carRepository.findAllOrderByPicturesCountDescMakeAsc();

        return cars.stream().map(Car::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElse(null);
    }
}
