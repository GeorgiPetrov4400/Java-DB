package com.example.xml.productShop.entities.categoryDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryImportDTO {

    @XmlElement(name = "category")
    List<CategoryNameDTO> categories;

    public CategoryImportDTO() {
        this.categories = new ArrayList<>();
    }

    public List<CategoryNameDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryNameDTO> categories) {
        this.categories = categories;
    }
}
