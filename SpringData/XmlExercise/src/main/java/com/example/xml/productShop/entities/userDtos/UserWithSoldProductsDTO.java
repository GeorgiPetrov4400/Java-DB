package com.example.xml.productShop.entities.userDtos;

import com.example.xml.productShop.entities.productDtos.ProductsSoldDTO;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductsDTO {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private List<ProductsSoldDTO> sellingProducts;

    public UserWithSoldProductsDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductsSoldDTO> getSellingProducts() {
        return sellingProducts;
    }

    public void setSellingProducts(List<ProductsSoldDTO> sellingProducts) {
        this.sellingProducts = sellingProducts;
    }
}
