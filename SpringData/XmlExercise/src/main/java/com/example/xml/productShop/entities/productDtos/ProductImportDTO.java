package com.example.xml.productShop.entities.productDtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportDTO {

    @XmlElement(name = "product")
    private List<ProductNameAndPriceDTO> products;

    public ProductImportDTO() {
    }

    public List<ProductNameAndPriceDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductNameAndPriceDTO> products) {
        this.products = products;
    }
}
