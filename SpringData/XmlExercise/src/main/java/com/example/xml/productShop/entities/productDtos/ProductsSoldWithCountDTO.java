package com.example.xml.productShop.entities.productDtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsSoldWithCountDTO {

    @XmlAttribute
    private Integer count;

    @XmlElement(name = "product")
    private List<ProductBasicInfo> products;

    public ProductsSoldWithCountDTO() {
    }

    public ProductsSoldWithCountDTO(List<ProductBasicInfo> products) {
        this.products = products;
        this.count = products.size();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductBasicInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBasicInfo> products) {
        this.products = products;
    }
}
