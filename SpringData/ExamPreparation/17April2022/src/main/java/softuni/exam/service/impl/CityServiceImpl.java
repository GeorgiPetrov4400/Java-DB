package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.cityDtos.CityImportDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.CountryService;
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
public class CityServiceImpl implements CityService {

    private final Path IMPORT_CITIES_JSON =
            Path.of("src", "main", "resources", "files", "json", "cities.json");

    private final CityRepository cityRepository;
    private final CountryService countryService;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CountryService countryService, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.countryService = countryService;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(IMPORT_CITIES_JSON);
    }

    @Override
    public String importCities() throws IOException {
        String json = readCitiesFileContent();

        CityImportDTO[] cityImportDTOS = gson.fromJson(json, CityImportDTO[].class);

        return Arrays.stream(cityImportDTOS).map(this::importCity).collect(Collectors.joining("\n"));
    }

//    @Override
//    public City findById(Long id) {
//        return this.cityRepository.findById(id).orElse(null);
//    }

    @Override
    public Optional<City> findById(Long id) {
        return this.cityRepository.findById(id);
    }

    private String importCity(CityImportDTO cityImportDTO) {
        Set<ConstraintViolation<CityImportDTO>> violations = validationUtil.violation(cityImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid city";
        }

        Optional<City> findCityByName = this.cityRepository.findByCityName(cityImportDTO.getCityName());

        if (findCityByName.isPresent()) {
            return "Invalid city";
        }

        City city = modelMapper.map(cityImportDTO, City.class);
        Optional<Country> countryById = this.countryService.findById(cityImportDTO.getCountry());
        city.setCountry(countryById.get());

        this.cityRepository.save(city);
        return String.format("Successfully imported city %s - %d", city.getCityName(), city.getPopulation());
    }
}
