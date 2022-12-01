package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.forecastDtos.ForecastImportDTO;
import softuni.exam.models.dto.forecastDtos.ForecastImportRootDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.enums.DaysOfWeek;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

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
public class ForecastServiceImpl implements ForecastService {

    private final Path IMPORT_FORECASTS_XML =
            Path.of("src", "main", "resources", "files", "xml", "forecasts.xml");

    private final ForecastRepository forecastRepository;
    private final CityService cityService;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, CityService cityService, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.forecastRepository = forecastRepository;
        this.cityService = cityService;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(IMPORT_FORECASTS_XML);
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
//        StringBuilder builder = new StringBuilder();
//
//        ForecastImportRootDTO forecastImportRootDTO =
//                xmlParser.fromFile(IMPORT_FORECASTS_XML.toString(), ForecastImportRootDTO.class);
//
//        forecastImportRootDTO.getForecasts().stream()
//                .filter(forecastImportDTO -> {
//                    boolean isValid = validationUtil.isValid(forecastImportDTO);
//
//                    Optional<Forecast> forecast = forecastRepository.findForecastByDayOfWeekAndCityId
//                            (forecastImportDTO.getDayOfWeek(), forecastImportDTO.getCity());
//
//                    City city = cityService.findById(forecastImportDTO.getCity());
//
//                    if (forecast.isPresent() || city == null) {
//                        isValid = false;
//                    }
//
//                    builder.append(isValid ? String.format("Successfully imported forecast %s - %s",
//                                    forecastImportDTO.getDayOfWeek(), forecastImportDTO.getMaxTemperature()) :
//                                    "Invalid forecast")
//                            .append(System.lineSeparator());
//
//                    return isValid;
//                })
//                .map(forecastImportDTO -> {
//                    Forecast forecast = modelMapper.map(forecastImportDTO, Forecast.class);
//                    forecast.setCity(cityService.findById(forecastImportDTO.getCity()));
//                    return forecast;
//                })
//                .forEach(forecastRepository::save);
//
//        return builder.toString();

        ForecastImportRootDTO forecastImportRootDTO =
                xmlParser.fromFile(IMPORT_FORECASTS_XML.toString(), ForecastImportRootDTO.class);

        return forecastImportRootDTO.getForecasts().stream()
                .map(this::importForecast).collect(Collectors.joining("\n"));

    }

    private String importForecast(ForecastImportDTO forecastImportDTO) {
        Set<ConstraintViolation<ForecastImportDTO>> violations = validationUtil.violation(forecastImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid forecast";
        }

//        Optional<Forecast> findExistForecast =
//                this.forecastRepository.findForecastByDayOfWeekAndCityId
//                        (forecastImportDTO.getDayOfWeek(), forecastImportDTO.getCity());
//        City city = this.cityService.findById(forecastImportDTO.getCity());

        Optional<Forecast> findExistForecast =
                this.forecastRepository.findByCity_IdAndDayOfWeek
                        (forecastImportDTO.getCity(), forecastImportDTO.getDayOfWeek());

        Optional<City> city = this.cityService.findById(forecastImportDTO.getCity());


        if (findExistForecast.isPresent() || city.isEmpty()) {
            return "Invalid forecast";
        }

        Forecast forecast = modelMapper.map(forecastImportDTO, Forecast.class);

        forecast.setCity(city.get());

        this.forecastRepository.save(forecast);

        return String.format("Successfully imported forecast %s - %s",
                forecast.getDayOfWeek(), forecast.getMaxTemperature());
    }

    @Override
    public String exportForecasts() {
        List<Forecast> forecastByDayOfWeekAndCityPopulation =
                this.forecastRepository.findForecastsByDayOfWeekAndCityPopulationLessThanOrderByMaxTemperatureDescIdAsc
                        (DaysOfWeek.valueOf("SUNDAY"), 150000);

        return forecastByDayOfWeekAndCityPopulation.stream()
                .map(Forecast::toString).collect(Collectors.joining(System.lineSeparator()));
    }
}
