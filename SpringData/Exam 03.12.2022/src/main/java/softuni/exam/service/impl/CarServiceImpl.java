package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.carDtos.CarImportDTO;
import softuni.exam.models.dto.carDtos.CarImportRootDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private static final Path IMPORT_CARS_XML =
            Path.of("src", "main", "resources", "files", "xml", "cars.xml");

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(IMPORT_CARS_XML);
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        CarImportRootDTO carImportRootDTO = xmlParser.fromFile(IMPORT_CARS_XML.toString(), CarImportRootDTO.class);

        return carImportRootDTO.getCars().stream()
                .map(this::importCar).collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public Car findById(Long id) {
        return this.carRepository.findById(id).orElse(null);
    }

    private String importCar(CarImportDTO carImportDTO) {
        Set<ConstraintViolation<CarImportDTO>> violations = validationUtil.violation(carImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid car";
        }

        Optional<Car> findByPlateNumber = this.carRepository.findByPlateNumber(carImportDTO.getPlateNumber());

        if (findByPlateNumber.isPresent()) {
            return "Invalid car";
        }

        Car car = modelMapper.map(carImportDTO, Car.class);
        this.carRepository.save(car);
        return String.format("Successfully imported car %s - %s", car.getCarMake(), car.getCarModel());
    }
}
