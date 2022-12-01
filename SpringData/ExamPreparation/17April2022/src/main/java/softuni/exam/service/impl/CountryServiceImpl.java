package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.countryDtos.CountryImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CountryServiceImpl implements CountryService {

    private final Path IMPORT_COUNTRIES_JSON =
            Path.of("src", "main", "resources", "files", "json", "countries.json");

    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(IMPORT_COUNTRIES_JSON);
    }

    @Override
    public String importCountries() throws IOException {
        String json = readCountriesFromFile();

        CountryImportDTO[] countryImportDTOS = gson.fromJson(json, CountryImportDTO[].class);

        List<String> countryResult = new ArrayList<>();

        for (CountryImportDTO countryImportDTO : countryImportDTOS) {
            Set<ConstraintViolation<CountryImportDTO>> violation = validationUtil.violation(countryImportDTO);

            if (violation.isEmpty()) {
                Optional<Country> findCountryByName =
                        this.countryRepository.findByCountryName(countryImportDTO.getCountryName());

                if (findCountryByName.isEmpty()) {
                    Country country = modelMapper.map(countryImportDTO, Country.class);
                    this.countryRepository.save(country);

                    String message = String.format("Successfully imported country %s - %s",
                            country.getCountryName(), country.getCurrency());
                    countryResult.add(message);
                } else {
                    countryResult.add("Invalid country");
                }
            } else {
                countryResult.add("Invalid country");
            }
        }

        return String.join("\n", countryResult);
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }
}
