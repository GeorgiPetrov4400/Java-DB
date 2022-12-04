package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.taskDtos.TaskImportDTO;
import softuni.exam.models.dto.taskDtos.TaskImportRootDTO;
import softuni.exam.models.entity.*;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.MechanicService;
import softuni.exam.service.PartService;
import softuni.exam.service.TaskService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Path IMPORT_TASKS_XML =
            Path.of("src", "main", "resources", "files", "xml", "tasks.xml");

    private final TaskRepository taskRepository;
    private final MechanicService mechanicService;
    private final CarService carService;
    private final PartService partService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, MechanicService mechanicService, CarService carService, PartService partService, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.taskRepository = taskRepository;
        this.mechanicService = mechanicService;
        this.carService = carService;
        this.partService = partService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.taskRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(IMPORT_TASKS_XML);
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        TaskImportRootDTO taskImportRootDTO =
                xmlParser.fromFile(IMPORT_TASKS_XML.toString(), TaskImportRootDTO.class);

        return taskImportRootDTO.getTasks().stream()
                .map(this::importTask).collect(Collectors.joining(System.lineSeparator()));
    }

    private String importTask(TaskImportDTO taskImportDTO) {
        Set<ConstraintViolation<TaskImportDTO>> violations = validationUtil.violation(taskImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid task";
        }

        Optional<Mechanic> findByFirstName =
                this.mechanicService.findByFirstName(taskImportDTO.getMechanic().getFirstName());

        if (findByFirstName.isEmpty()) {
            return "Invalid task";
        }

        Task task = modelMapper.map(taskImportDTO, Task.class);

        Car car = this.carService.findById(taskImportDTO.getCar().getId());
        Part part = this.partService.findById(taskImportDTO.getPart().getId());
        task.setCar(car);
        task.setPart(part);
        task.setMechanic(findByFirstName.get());

        this.taskRepository.save(task);
        return String.format("Successfully imported task %.2f", taskImportDTO.getPrice());
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {

        List<Task> tasks = this.taskRepository.findAllByCarCarTypeOrderByPriceDesc(CarType.coupe);

        return tasks.stream().map(Task::toString).collect(Collectors.joining(System.lineSeparator()));
    }
}
