package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.laptopDto.LaptopImportDTO;
import exam.model.entity.Laptop;
import exam.model.entity.Shop;
import exam.repository.LaptopRepository;
import exam.service.LaptopService;
import exam.service.ShopService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {

    private static final Path IMPORT_LAPTOPS_JSON =
            Path.of("src", "main", "resources", "files", "json", "laptops.json");

    private final LaptopRepository laptopRepository;
    private final ShopService shopService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopService shopService, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.laptopRepository = laptopRepository;
        this.shopService = shopService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(IMPORT_LAPTOPS_JSON);
    }

    @Override
    public String importLaptops() throws IOException {
        LaptopImportDTO[] laptopImportDTOS =
                gson.fromJson(readLaptopsFileContent(), LaptopImportDTO[].class);

        return Arrays.stream(laptopImportDTOS)
                .map(this::importLaptop).collect(Collectors.joining(System.lineSeparator()));
    }

    private String importLaptop(LaptopImportDTO laptopImportDTO) {
        Set<ConstraintViolation<LaptopImportDTO>> violations = validationUtil.violation(laptopImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid laptop";
        }

        Optional<Laptop> findLaptopByMacAddress =
                this.laptopRepository.findByMacAddress(laptopImportDTO.getMacAddress());

        if (findLaptopByMacAddress.isPresent()) {
            return "Invalid laptop";
        }

        Laptop laptop = modelMapper.map(laptopImportDTO, Laptop.class);

        Shop shop = this.shopService.findByName(laptopImportDTO.getShop().getName());
        laptop.setShop(shop);

        this.laptopRepository.save(laptop);
        return String.format("Successfully imported Laptop %s - %.2f - %d - %d",
                laptopImportDTO.getMacAddress(), laptopImportDTO.getCpuSpeed(),
                laptopImportDTO.getRam(), laptopImportDTO.getStorage());
    }

    @Override
    public String exportBestLaptops() {
        List<Laptop> laptops = this.laptopRepository.findAllByOrderByCpuSpeedDescRamDescStorageDescMacAddressAsc();

        return laptops.stream().map(Laptop::toString).collect(Collectors.joining(System.lineSeparator()));
    }
}
