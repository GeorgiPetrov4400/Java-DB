package exam.model.dto.laptopDto;

import exam.model.dto.shopDto.ShopNameDTO;
import exam.model.entity.WarrantyType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class LaptopImportDTO {

    @Size(min = 9)
    @NotNull
    private String macAddress;

    @DecimalMin(value = "1")
    @NotNull
    private Double cpuSpeed;

    @Min(8)
    @Max(128)
    @NotNull
    private int ram;

    @Min(128)
    @Max(1024)
    @NotNull
    private int storage;

    @Size(min = 10)
    @NotNull
    private String description;

    @DecimalMin(value = "1")
    @NotNull
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @NotNull
    private WarrantyType warrantyType;

    @NotNull
    private ShopNameDTO shop;

    public LaptopImportDTO() {
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Double getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(Double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
    }

    public ShopNameDTO getShop() {
        return shop;
    }

    public void setShop(ShopNameDTO shop) {
        this.shop = shop;
    }
}
