package softuni.exam.models.dto.partDtos;

import javax.validation.constraints.*;

public class PartImportDTO {

    @Size(min = 2, max = 19)
    @NotNull
    private String partName;

    @DecimalMin(value = "10")
    @DecimalMax(value = "2000")
    @NotNull
    private Double price;

    @Min(1)
    @NotNull
    private Integer quantity;

    public PartImportDTO() {
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
