package com.example.xml.productShop.entities.categoryDtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryDataDTO {

    @XmlElement(name = "category")
    private List<CategoryStatisticsDTO> categories;

    public CategoryDataDTO() {
    }

    public CategoryDataDTO(List<CategoryStatisticsDTO> categories) {
        this.categories = categories;
    }

    public List<CategoryStatisticsDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryStatisticsDTO> categories) {
        this.categories = categories;
    }
}

