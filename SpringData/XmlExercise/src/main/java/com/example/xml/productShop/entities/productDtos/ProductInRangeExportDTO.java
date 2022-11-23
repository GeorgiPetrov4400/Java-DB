package com.example.xml.productShop.entities.productDtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductInRangeExportDTO {

    @XmlElement(name = "product")
    private List<ProductWithNamePriceSellerDTO> products;

    public ProductInRangeExportDTO() {
    }

    public ProductInRangeExportDTO(List<ProductWithNamePriceSellerDTO> products) {
        this.products = products;
    }

    public List<ProductWithNamePriceSellerDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWithNamePriceSellerDTO> products) {
        this.products = products;
    }
}
