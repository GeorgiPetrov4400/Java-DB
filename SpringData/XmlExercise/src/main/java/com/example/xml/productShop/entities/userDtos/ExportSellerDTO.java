package com.example.xml.productShop.entities.userDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportSellerDTO {

    @XmlElement(name = "user")
    private List<UserWithSoldProductsDTO> users;

    public ExportSellerDTO() {
    }

    public ExportSellerDTO(List<UserWithSoldProductsDTO> users) {
        this.users = users;
    }
}
