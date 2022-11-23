package com.example.xml.productShop.entities.userDtos;

import com.example.xml.productShop.entities.productDtos.ProductsSoldWithCountDTO;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithProductsDTO {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute
    private Integer age;

    @XmlElement(name = "sold-products")
    private ProductsSoldWithCountDTO products;

    public UserWithProductsDTO() {
    }

    public UserWithProductsDTO(String firstName, String lastName, Integer age, ProductsSoldWithCountDTO products) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.products = products;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProductsSoldWithCountDTO getProducts() {
        return products;
    }

    public void setProducts(ProductsSoldWithCountDTO products) {
        this.products = products;
    }

    public int soldProductsCount() {
        return products.getCount();
    }
}
